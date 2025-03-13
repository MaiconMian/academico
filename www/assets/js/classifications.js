function populateTable(buttonTemplateCallback) {
    const table = $('#genres').DataTable({
        responsive: true, 
        autoWidth: false, 
        scrollX: true, 
        ajax: {
            url: 'http://localhost:8090/api/v1/classifications', 
            dataSrc: '' 
        },
        columns: [
            { data: 'id' },
            { data: 'classificationName' },  
            { data: 'classificationDescription' },  
            { data: 'classificationAge' },  
            {
                data: null,
                render: (data) => buttonTemplateCallback(data)
            }
        ]
    });
}

$(document).ready(function () {
    populateTable(classification => `
        <button type="button" class="btn btn-success btn-edit" data-id="${classification.id}">
                <i class="fa fa-pencil-square-o"></i> 
        </button>
        <button type="button" class="btn btn-danger btn-delete" data-id="${classification.id}">
                <i class="fa fa-trash"></i> 
        </button>
    `);

    $(document).on('click', '.btn-delete', function () {
        const classificationId = $(this).data('id');
        $('#deleteError').hide();
        $('#modalDeleteLabel').text("Delete classification");
        $('.modal-body-delet').empty().append("<p>Are you sure you want to delete this classification?</p>");
        $('#modalDelete').modal('show');

        $('#confirmDelete').off().on('click', function () {
            $.ajax({
                url: `http://localhost:8090/api/v1/classifications/${classificationId}`,
                type: 'DELETE',
                success: function () {
                    $('#modalDelete').modal('hide');
                    $('#genres').DataTable().ajax.reload();
                },
                error: function () {
                    $('#deleteError').text('Error: Failed to delete classification').show();
                }
            });
        });
    });

    $(document).on('click', '.btn-edit', function () {
        const classificationId = $(this).data('id');
        $.ajax({
            url: `http://localhost:8090/api/v1/classifications/${classificationId}`,
            type: 'GET',
            success: function (classification) {
                $('#editClassificationId').val(classification.id);  
                $('#editClassificationName').val(classification.classificationName);
                $('#editClassificationDescription').val(classification.classificationDescription);
                $('#editClassificationAge').val(classification.classificationAge);
                $('#modalEditLabel').text('Edit Classification');
                $('#modalEdit').modal('show');
            },
            error: function (error) {
                alert('Erro ao carregar os dados da classificação.');
                console.error(error);
            }
        });
    });

    $('#btnAddClassification').click(function () {
        $('#editClassificationId').val('');  
        $('#editClassificationName').val('');
        $('#editClassificationDescription').val('');
        $('#editClassificationAge').val('');
        $('#modalEditLabel').text('Add Classification');
        $('#modalEdit').modal('show');
    });

    $('#editForm').on('submit', function (e) {
        e.preventDefault();

        const classificationId = $('#editClassificationId').val(); 
        const updatedClassification = {
            classificationName: $('#editClassificationName').val(),
            classificationDescription: $('#editClassificationDescription').val(),
            classificationAge: $('#editClassificationAge').val()
        };

        if (classificationId) {
            $.ajax({
                url: `http://localhost:8090/api/v1/classifications/${classificationId}`,
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(updatedClassification),
                success: function () {
                    alert('Success when update');
                    $('#genres').DataTable().ajax.reload();
                    $('#modalEdit').modal('hide'); 
                },
                error: function (error) {
                    alert('Erro ao atualizar a classificação.');
                    console.error(error);
                }
            });
        } else {
            $.ajax({
                url: 'http://localhost:8090/api/v1/classifications',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(updatedClassification),
                success: function () {
                    alert('Success when creating');
                    $('#genres').DataTable().ajax.reload();
                    $('#modalEdit').modal('hide'); 
                },
                error: function (error) {
                    alert('Erro ao criar a classificação.');
                    console.error(error);
                }
            });
        }
    });
});
