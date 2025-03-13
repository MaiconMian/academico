function populateTable(buttonTemplateCallback) {
    const table = $('#genres').DataTable({
        responsive: true, 
        autoWidth: false, 
        scrollX: true, 
        ajax: {
            url: 'http://localhost:8090/api/v1/genres', 
            dataSrc: '' 
        },
        columns: [
            { data: 'id' },
            { data: 'genreName' },  
            { data: 'genreDescription' },  
            {
                data: null,
                render: (data) => buttonTemplateCallback(data)
            }
        ]
    });
}

$(document).ready(function () {
    // Função que popula a tabela com os dados
    populateTable(genre => `
        <button type="button" class="btn btn-success btn-edit" data-id="${genre.id}">
                <i class="fa fa-pencil-square-o"></i> 
        </button>
        <button type="button" class="btn btn-danger btn-delete" data-id="${genre.id}">
                <i class="fa fa-trash"></i> 
        </button>
    `);

    // excluir 
    $(document).on('click', '.btn-delete', function () {
        const genreId = $(this).data('id');
        $('#deleteError').hide();
        $('#modalDeleteLabel').text("Delete section");
        $('.modal-body-delet').empty().append("<p>Are you sure you want to delete this section?</p>");
        $('#modalDelete').modal('show');

        $('#confirmDelete').off().on('click', function () {
            $.ajax({
                url: `http://localhost:8090/api/v1/genres/${genreId}`,
                type: 'DELETE',
                success: function () {
                    $('#modalDelete').modal('hide');
                    $('#genres').DataTable().ajax.reload();
                },
                error: function () {
                    $('#deleteError').text('Error: Failed to delete section').show();
                }
            });
        });
    });

    // editar 
    $(document).on('click', '.btn-edit', function () {
        const genreId = $(this).data('id');
        $.ajax({
            url: `http://localhost:8090/api/v1/genres/${genreId}`,
            type: 'GET',
            success: function (genre) {
                $('#editGenreId').val(genre.id);  
                $('#editGenreName').val(genre.genreName);
                $('#editGenreDescription').val(genre.genreDescription);
                $('#modalEditLabel').text('Edit Genre');
                $('#modalEdit').modal('show');
            },
            error: function (error) {
                alert('Erro ao carregar os dados do gênero.');
                console.error(error);
            }
        });
    });

    $('#btnAddGenre').click(function () {
        $('#editGenreId').val('');  
        $('#editGenreName').val('');
        $('#editGenreDescription').val('');
        $('#modalEditLabel').text('Add Genre');
        $('#modalEdit').modal('show');
    });

    // criar
    $('#editForm').on('submit', function (e) {
        e.preventDefault();

        const genreId = $('#editGenreId').val(); 
        const updatedGenre = {
            genreName: $('#editGenreName').val(),
            genreDescription: $('#editGenreDescription').val()
        };

        if (genreId) {
            $.ajax({
                url: `http://localhost:8090/api/v1/genres/${genreId}`,
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(updatedGenre),
                success: function () {
                    alert('Success when update');
                    $('#genres').DataTable().ajax.reload();
                    $('#modalEdit').modal('hide'); 
                },
                error: function (error) {
                    alert('Erro ao atualizar o gênero.');
                    console.error(error);
                }
            });
        } else {
            $.ajax({
                url: 'http://localhost:8090/api/v1/genres',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(updatedGenre),
                success: function () {
                    alert('Success when creating');
                    $('#genres').DataTable().ajax.reload();
                    $('#modalEdit').modal('hide'); 
                },
                error: function (error) {
                    alert('Erro ao criar o gênero.');
                    console.error(error);
                }
            });
        }
    });
});

