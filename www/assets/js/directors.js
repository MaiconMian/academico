let table;

function populateTable(buttonTemplateCallback) {
    if ($.fn.dataTable.isDataTable('#genres')) {
        table = $('#genres').DataTable();
    } else {
        table = $('#genres').DataTable({
        responsive: true, 
        autoWidth: false, 
        scrollX: true, 
            ajax: {
                url: 'http://localhost:8090/api/v1/directors',
                dataSrc: ''
            },
            columns: [
                { data: 'id' },
                { data: 'directorName' },
                { data: 'directorLastName' },
                { data: 'directorDescription' },
                {
                    data: null,
                    render: (data) => buttonTemplateCallback(data)
                }
            ]
        });
    }
}

$(document).ready(function () {
    populateTable(director => `
        <button type="button" class="btn btn-success btn-edit" data-id="${director.id}">
            <i class="fa fa-pencil-square-o"></i> 
        </button>
        <button type="button" class="btn btn-danger btn-delete" data-id="${director.id}">
            <i class="fa fa-trash"></i> 
        </button>
    `);

    $(document).on('click', '.btn-delete', function () {
        const directorId = $(this).data('id');
        $('#deleteError').hide();
        $('#modalDeleteLabel').text("Delete director");
        $('.modal-body-delet').empty().append("<p>Are you sure you want to delete this director?</p>");
        $('#modalDelete').modal('show');

        $('#confirmDelete').off().on('click', function () {
            $.ajax({
                url: `http://localhost:8090/api/v1/directors/${directorId}`,
                type: 'DELETE',
                success: function () {
                    $('#modalDelete').modal('hide');
                    $('#genres').DataTable().ajax.reload();
                },
                error: function () {
                    $('#deleteError').text('Error: Failed to delete director').show();
                }
            });
        });
    });

    $(document).on('click', '.btn-edit', function () {
        const directorId = $(this).data('id');
        $.ajax({
            url: `http://localhost:8090/api/v1/directors/${directorId}`,
            type: 'GET',
            success: function (director) {
                $('#editDirectorId').val(director.id);
                $('#editDirectorName').val(director.directorName);
                $('#editDirectorLastName').val(director.directorLastName);
                $('#editDirectorDescription').val(director.directorDescription);
                $('#modalEditLabel').text('Editar Diretor');
                $('#modalEdit').modal('show');
            },
            error: function (error) {
                alert('Erro ao carregar os dados do diretor.');
                console.error(error);
            }
        });
    });

    $('#btnAddDirector').click(function () {
        $('#editDirectorId').val('');
        $('#editDirectorName').val('');
        $('#editDirectorLastName').val('');
        $('#editDirectorDescription').val('');
        $('#modalEditLabel').text('Adicionar Diretor');
        $('#modalEdit').modal('show');
    });

    $('#editForm').on('submit', function (e) {
        e.preventDefault();

        const directorId = $('#editDirectorId').val();
        const updatedDirector = {
            directorName: $('#editDirectorName').val(),
            directorLastName: $('#editDirectorLastName').val(),
            directorDescription: $('#editDirectorDescription').val()
        };

        if (directorId) {
            $.ajax({
                url: `http://localhost:8090/api/v1/directors/${directorId}`,
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(updatedDirector),
                success: function () {
                    alert('Success when update');
                    $('#genres').DataTable().ajax.reload();
                    $('#modalEdit').modal('hide');
                },
                error: function (error) {
                    alert('Erro ao atualizar o diretor.');
                    console.error(error);
                }
            });
        } else {
            $.ajax({
                url: 'http://localhost:8090/api/v1/directors',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(updatedDirector),
                success: function () {
                    alert('Success when creating');
                    $('#genres').DataTable().ajax.reload();
                    $('#modalEdit').modal('hide');
                },
                error: function (error) {
                    alert('Erro ao criar o diretor.');
                    console.error(error);
                }
            });
        }
    });
});
