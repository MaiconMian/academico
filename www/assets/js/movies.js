function populateTable(buttonTemplateCallback) {
    const table = $('#genres').DataTable({
        responsive: true, 
        autoWidth: false, 
        scrollX: true, 
        ajax: {
            url: 'http://localhost:8090/api/v1/movies', // Endereço do controlador de Filmes
            dataSrc: '' // DataSource configurado para pegar diretamente o array de objetos
        },
        columns: [
            { data: 'id' }, // Filme ID
            { data: 'movieName' }, // Nome do filme
            { data: 'movieSynopsis' }, // Sinopse do filme
            { data: 'movieReleaseYear' }, // Ano de lançamento
            { 
                data: 'classification.classificationName', // Nome da classificação
                render: (data) => data || 'Sem Classificação'
            },
            { 
                data: 'director.directorName', // Nome do diretor
                render: (data) => data || 'Sem Diretor'
            },
            {
                data: null,
                render: (data) => buttonTemplateCallback(data) // Botões Editar e Excluir
            }
        ]
    });
}

$(document).ready(function () {
    // Função para popular a tabela com os filmes
    populateTable(movie => `
        <button type="button" class="btn btn-success btn-edit" data-id="${movie.id}">
            <i class="fa fa-pencil-square-o"></i> 
        </button>
        <button type="button" class="btn btn-danger btn-delete" data-id="${movie.id}">
            <i class="fa fa-trash"></i> 
        </button>
    `);

    // Excluir filme
    $(document).on('click', '.btn-delete', function () {
        const movieId = $(this).data('id');
        $('#deleteError').hide();
        $('#modalDeleteLabel').text("Delete movie");
        $('.modal-body-delet').empty().append("<p>Are you sure you want to delete this movie?</p>");
        $('#modalDelete').modal('show');

        $('#confirmDelete').off().on('click', function () {
            $.ajax({
                url: `http://localhost:8090/api/v1/movies/${movieId}`,
                type: 'DELETE',
                success: function () {
                    $('#modalDelete').modal('hide');
                    $('#genres').DataTable().ajax.reload();
                },
                error: function () {
                    $('#deleteError').text('Error: Failed to delete movie').show();
                }
            });
        });
    });

    // Editar filme
    $(document).on('click', '.btn-edit', function () {
        const movieId = $(this).data('id');

        // Carregar classificações
        $.ajax({
            url: 'http://localhost:8090/api/v1/classifications', // Endereço da API de classificações
            type: 'GET',
            success: function (classifications) {
                const classificationSelect = $('#editClassificationId');
                classificationSelect.empty();
                classificationSelect.append('<option value="">Select Classification</option>');

                classifications.forEach(function (classification) {
                    classificationSelect.append(`<option value="${classification.id}">${classification.classificationName}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar as classificações.');
                console.error(error);
            }
        });

        // Carregar diretores
        $.ajax({
            url: 'http://localhost:8090/api/v1/directors', // Endereço da API de diretores
            type: 'GET',
            success: function (directors) {
                const directorSelect = $('#editDirectorId');
                directorSelect.empty();
                directorSelect.append('<option value="">Select Director</option>');

                directors.forEach(function (director) {
                    directorSelect.append(`<option value="${director.id}">${director.directorName}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar os diretores.');
                console.error(error);
            }
        });

        // Carregar gêneros
        $.ajax({
            url: 'http://localhost:8090/api/v1/genres', 
            type: 'GET',
            success: function (genres) {
                const genreSelect = $('#editGenreId');
                genreSelect.empty();

                genres.forEach(function (genre) {
                    genreSelect.append(`<option value="${genre.id}">${genre.genreName}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar os gêneros.');
                console.error(error);
            }
        });

        // Carregar atores
        $.ajax({
            url: 'http://localhost:8090/api/v1/actors', // Endereço da API de atores
            type: 'GET',
            success: function (actors) {
                const actorSelect = $('#editActorId');
                actorSelect.empty();

                actors.forEach(function (actor) {
                    actorSelect.append(`<option value="${actor.id}">${actor.actorName}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar os atores.');
                console.error(error);
            }
        });

        // Carregar dados do filme para edição
        $.ajax({
            url: `http://localhost:8090/api/v1/movies/${movieId}`, // Endpoint do controlador
            type: 'GET',
            success: function (movie) {
                $('#editMovieId').val(movie.id);
                $('#editMovieName').val(movie.movieName);
                $('#editMovieSynopsis').val(movie.movieSynopsis);
                $('#editReleaseYear').val(movie.movieReleaseYear);
                $('#editClassificationId').val(movie.classification?.id); // Preencher classificação
                $('#editDirectorId').val(movie.director?.id); // Preencher diretor
                $('#modalEditLabel').text('Edit Movie');
                $('#modalEdit').modal('show');
            },
            error: function (error) {
                alert('Erro ao carregar os dados do filme.');
                console.error(error);
            }
        });
    });

    // Adicionar novo filme
    $('#btnAddMovie').click(function () {
        // Requisições para carregar dados (classificações, diretores, gêneros e atores)
        $.ajax({
            url: 'http://localhost:8090/api/v1/classifications',
            type: 'GET',
            success: function (classifications) {
                const classificationSelect = $('#editClassificationId');
                classificationSelect.empty();
                classificationSelect.append('<option value="">Select Classification</option>');

                classifications.forEach(function (classification) {
                    classificationSelect.append(`<option value="${classification.id}">${classification.classificationName}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar as classificações.');
                console.error(error);
            }
        });

        $.ajax({
            url: 'http://localhost:8090/api/v1/directors',
            type: 'GET',
            success: function (directors) {
                const directorSelect = $('#editDirectorId');
                directorSelect.empty();
                directorSelect.append('<option value="">Select Director</option>');

                directors.forEach(function (director) {
                    directorSelect.append(`<option value="${director.id}">${director.directorName}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar os diretores.');
                console.error(error);
            }
        });

        $.ajax({
            url: 'http://localhost:8090/api/v1/genres',
            type: 'GET',
            success: function (genres) {
                const genreSelect = $('#editGenreId');
                genreSelect.empty();

                genres.forEach(function (genre) {
                    genreSelect.append(`<option value="${genre.id}">${genre.genreName}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar os gêneros.');
                console.error(error);
            }
        });

        $.ajax({
            url: 'http://localhost:8090/api/v1/actors',
            type: 'GET',
            success: function (actors) {
                const actorSelect = $('#editActorId');
                actorSelect.empty();

                actors.forEach(function (actor) {
                    actorSelect.append(`<option value="${actor.id}">${actor.actorName}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar os atores.');
                console.error(error);
            }
        });

        // Limpar campos antes de exibir o modal
        $('#editMovieId').val('');
        $('#editMovieName').val('');
        $('#editMovieSynopsis').val('');
        $('#editReleaseYear').val('');
        $('#editClassificationId').val('');
        $('#editDirectorId').val('');
        $('#modalEditLabel').text('Add Movie');
        $('#modalEdit').modal('show');
    });

    // Envio do formulário de edição
    $('#editForm').on('submit', function (e) {
        e.preventDefault();

        const movieId = $('#editMovieId').val();
        const updatedMovie = {
            movieName: $('#editMovieName').val(),
            movieSynopsis: $('#editMovieSynopsis').val(),
            movieReleaseYear: $('#editReleaseYear').val(),
            classificationId: $('#editClassificationId').val(),
            directorId: $('#editDirectorId').val(),
            genremovies: $('#editGenreId').val(), // IDs dos gêneros
            movieactors: $('#editActorId').val() // IDs dos atores
        };

        const method = movieId ? 'PUT' : 'POST';
        const url = movieId ? `http://localhost:8090/api/v1/movies/${movieId}` : 'http://localhost:8090/api/v1/movies';
        
        $.ajax({
            url: url,
            type: method,
            data: JSON.stringify(updatedMovie),
            contentType: 'application/json',
            success: function () {
                alert('Success when creating');
                $('#genres').DataTable().ajax.reload();
                $('#modalEdit').modal('hide');
            },
            error: function (error) {
                alert('Erro ao salvar o filme.');
                console.error(error);
            }
        });
    });
});
