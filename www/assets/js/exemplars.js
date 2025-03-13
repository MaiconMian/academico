function populateTable(buttonTemplateCallback) {
    const table = $('#genres').DataTable({
        responsive: true, 
        autoWidth: false, 
        scrollX: true, 
        ajax: {
            url: 'http://localhost:8090/api/v1/exemplars', 
            dataSrc: '' 
        },
        columns: [
            { data: 'id' }, 
            {
                data: 'movie',
                render: (data) => data ? data.movieName : 'Sem Filme' // Exibindo o nome do filme
            },
            {
                data: 'exemplarBuyDate',
                render: (data) => {
                    // Verificar se exemplarBuyDate é um timestamp ou já é uma string
                    const date = new Date(data);
                    if (!isNaN(date)) {
                        return date.toLocaleDateString('pt-BR'); // Exibe a data no formato 'DD/MM/YYYY'
                    }
                    return 'Data inválida';
                }
            },  
            {
                data: null,
                render: (data) => buttonTemplateCallback(data) 
            }
        ]
    });
}

$(document).ready(function () {
    // Função para popular a tabela com os exemplares
    populateTable(exemplar => `
        <button type="button" class="btn btn-success btn-edit" data-id="${exemplar.id}">
            <i class="fa fa-pencil-square-o"></i> 
        </button>
        <button type="button" class="btn btn-danger btn-delete" data-id="${exemplar.id}">
            <i class="fa fa-trash"></i> 
        </button>
    `);

    // Excluir exemplar
    $(document).on('click', '.btn-delete', function () {
        const exemplarId = $(this).data('id');
        $('#deleteError').hide();
        $('#modalDeleteLabel').text("Delete exemplar");
        $('.modal-body-delet').empty().append("<p>Are you sure you want to delete this exemplar?</p>");
        $('#modalDelete').modal('show');

        $('#confirmDelete').off().on('click', function () {
            $.ajax({
                url: `http://localhost:8090/api/v1/exemplars/${exemplarId}`,
                type: 'DELETE',
                success: function () {
                    $('#modalDelete').modal('hide');
                    $('#genres').DataTable().ajax.reload();
                },
                error: function () {
                    $('#deleteError').text('Error: Failed to delete exemplar').show();
                }
            });
        });
    });

    // Editar exemplar
    $(document).on('click', '.btn-edit', function () {
        console.log('Edit button clicked'); // Verificar se o clique está sendo registrado
        const exemplarId = $(this).data('id');
    
        // Carregar filmes ao abrir o modal de edição
        $.ajax({
            url: 'http://localhost:8090/api/v1/movies', // Endpoint da API de filmes
            type: 'GET',
            success: function (movies) {
                const movieSelect = $('#editMovieId');
                movieSelect.empty(); // Limpa as opções anteriores
                movieSelect.append('<option value="">Select Movie</option>'); // Adiciona a opção default
    
                movies.forEach(function (movie) {
                    movieSelect.append(`<option value="${movie.id}">${movie.movieName}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar os filmes.');
                console.error(error);
            }
        });
    
        // Carregar dados do exemplar para edição
        $.ajax({
            url: `http://localhost:8090/api/v1/exemplars/${exemplarId}`, // Endpoint do controlador
            type: 'GET',
            success: function (exemplar) {
                $('#editExemplarId').val(exemplar.id);
                $('#editMovieId').val(exemplar.movie.id); // Preencher o campo de filme com o filme atual
            
                let buyDate = exemplar.exemplarBuyDate;
            
                // Verifique se exemplarBuyDate é um objeto Date ou uma string válida
                if (buyDate instanceof Date && !isNaN(buyDate)) {
                    // Se for um objeto Date válido, converta para o formato 'DD/MM/YYYY'
                    const formattedDate = buyDate.toLocaleDateString('pt-BR'); // Utiliza o formato brasileiro
                    $('#editExemplarBuyDate').val(formattedDate); // Preenche o campo de data
                } else if (typeof buyDate === 'string') {
                    // Se exemplarBuyDate for uma string, apenas atribua (ou converta se necessário)
                    $('#editExemplarBuyDate').val(buyDate);
                } else {
                    // Caso contrário, trate o campo como vazio
                    $('#editExemplarBuyDate').val('');
                }
            
                $('#modalEditLabel').text('Edit Exemplar');
                $('#modalEdit').modal('show'); // Mostra o modal
            },
            error: function (error) {
                alert('Erro ao carregar os dados do exemplar.');
                console.error(error);
            }
        });
    });
    

    // Adicionar novo exemplar
    $('#btnAddExemplar').click(function () {
        $.ajax({
            url: 'http://localhost:8090/api/v1/movies',
            type: 'GET',
            success: function (movies) {
                const movieSelect = $('#editMovieId');
                movieSelect.empty(); 
                movieSelect.append('<option value="">Select Movie</option>'); // Adiciona a opção default

                movies.forEach(function (movie) {
                    movieSelect.append(`<option value="${movie.id}">${movie.movieName}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar os filmes.');
                console.error(error);
            }
        });

        $('#editExemplarId').val('');
        $('#editMovieId').val('');
        $('#editExemplarBuyDate').val('');
        $('#modalEditLabel').text('Add Exemplar');
        $('#modalEdit').modal('show');
    });

    $('#editForm').on('submit', function (e) {
        e.preventDefault();

        const exemplarId = $('#editExemplarId').val();
        const updatedExemplar = {
            movieId: $('#editMovieId').val(),
            exemplarBuyDate: $('#editExemplarBuyDate').val()
        };

        if (exemplarId) {
            $.ajax({
                url: `http://localhost:8090/api/v1/exemplars/${exemplarId}`, // Endpoint do controlador
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(updatedExemplar),
                success: function () {
                    alert('Success when update');
                    $('#genres').DataTable().ajax.reload();
                    $('#modalEdit').modal('hide');
                },
                error: function (error) {
                    alert('Erro ao atualizar o exemplar.');
                    console.error(error);
                }
            });
        } else {
            $.ajax({
                url: 'http://localhost:8090/api/v1/exemplars', // Endpoint do controlador
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(updatedExemplar),
                success: function () {
                    alert('Success when creating');
                    $('#genres').DataTable().ajax.reload();
                    $('#modalEdit').modal('hide');
                },
                error: function (error) {
                    alert('Erro ao criar o exemplar.');
                    console.error(error);
                }
            });
        }
    });
});
