
function populateTable(buttonTemplateCallback) {
    const table = $('#genres').DataTable({
        responsive: true, 
        autoWidth: false, 
        scrollX: true, 
        ajax: {
            url: 'http://localhost:8090/api/v1/actors',
            dataSrc: ''
        },
        columns: [
            { data: 'id' },
            { data: 'actorName' },
            { data: 'actorLastName' },
            {
                data: 'actorBirthdayDate',
                render: function(data) {
                    const date = new Date(data);
                    return date.toLocaleDateString(); 
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

    var slidebar = document.getElementById('slideBar');

    populateTable(actor => `
        <button type="button" class="btn btn-success btn-edit" data-id="${actor.id}">
                <i class="fa fa-pencil-square-o"></i> 
        </button>
        <button type="button" class="btn btn-danger btn-delete" data-id="${actor.id}">
                <i class="fa fa-trash"></i> 
        </button>
    `);

    $(document).on('click', '.btn-delete', function () {
        const actorId = $(this).data('id');
        $('#deleteError').hide();
        $('#modalDeleteLabel').text("Delete actor");
        $('.modal-body-delet').empty().append("<p>Are you sure you want to delete this actor?</p>");
        $('#modalDelete').modal('show');

        $('#confirmDelete').off().on('click', function () {
            $.ajax({
                url: `http://localhost:8090/api/v1/actors/${actorId}`,
                type: 'DELETE',
                success: function () {
                    $('#modalDelete').modal('hide');
                    $('#genres').DataTable().ajax.reload();
                },
                error: function () {
                    $('#deleteError').text('Error: Failed to delete actor').show();
                }
            });
        });
    });

    $(document).on('click', '.btn-edit', function () {
        const actorId = $(this).data('id');
        $.ajax({
            url: `http://localhost:8090/api/v1/actors/${actorId}`,
            type: 'GET',
            success: function (actor) {
                $('#editActorId').val(actor.id);
                $('#editActorName').val(actor.actorName);
                $('#editActorLastName').val(actor.actorLastName);
                $('#editActorBirthdayDate').val(actor.actorBirthdayDate); // Assumindo que a data já vem no formato esperado
                $('#modalEditLabel').text('Edit Actor');
                $('#modalEdit').modal('show');
            },
            error: function (error) {
                alert('Erro ao carregar os dados do ator.');
                console.error(error);
            }
        });
    });

    $('#btnAddActor').click(function () {
        $('#editActorId').val('');
        $('#editActorName').val('');
        $('#editActorLastName').val('');
        $('#editActorBirthdayDate').val('');
        $('#modalEditLabel').text('Add Actor');
        $('#modalEdit').modal('show');
    });

    $('#editForm').on('submit', function (e) {
        e.preventDefault();

        const actorId = $('#editActorId').val();
        const updatedActor = {
            actorName: $('#editActorName').val(),
            actorLastName: $('#editActorLastName').val(),
            actorBirthdayDate: $('#editActorBirthdayDate').val() // Data vem do input
        };

        if (actorId) {
            $.ajax({
                url: `http://localhost:8090/api/v1/actors/${actorId}`,
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(updatedActor),
                success: function () {
                    alert('Success when update');
                    $('#actors').DataTable().ajax.reload(); // Atualiza a tabela sem recarregar a página
                },
                error: function (error) {
                    alert('Erro ao atualizar o ator.');
                    console.error(error);
                }
            });
        } else {
            $.ajax({
                url: 'http://localhost:8090/api/v1/actors',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(updatedActor),
                success: function () {
                    alert('Success when creating');
                    $('#actors').DataTable().ajax.reload(); // Atualiza a tabela sem recarregar a página
                },
                error: function (error) {
                    alert('Erro ao criar o ator.');
                    console.error(error);
                }
            });
        }
    });
});
