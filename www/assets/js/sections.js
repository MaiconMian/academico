function populateTable(buttonTemplateCallback) {
    const table = $('#genres').DataTable({
        responsive: true, 
        autoWidth: false, 
        scrollX: true, 
        ajax: {
            url: 'http://localhost:8090/api/v1/sections', // Endereço do controlador de Seções
            dataSrc: '' // DataSource configurado para pegar diretamente o array de objetos
        },
        columns: [
            { data: 'id' }, // Seção ID
            {
                data: null,
                render: (data) => {
                    const classificationName = data.classification ? data.classification.classificationName : 'Sem Classificação';
                    return classificationName;
                } 
            },
            { data: 'sectionName' },  // Nome da seção
            { data: 'sectionDescription' },  // Descrição da seção
            {
                data: null,
                render: (data) => buttonTemplateCallback(data) 
            }
        ]
    });
}

$(document).ready(function () {
    // Função para popular a tabela com as seções
    populateTable(section => `
        <button type="button" class="btn btn-success btn-edit" data-id="${section.id}">
            <i class="fa fa-pencil-square-o"></i> 
        </button>
        <button type="button" class="btn btn-danger btn-delete" data-id="${section.id}">
            <i class="fa fa-trash"></i> 
        </button>
    `);

    // Excluir seção
    $(document).on('click', '.btn-delete', function () {
        const sectionId = $(this).data('id');
        $('#deleteError').hide();
        $('#modalDeleteLabel').text("Delete section");
        $('.modal-body-delet').empty().append("<p>Are you sure you want to delete this section?</p>");
        $('#modalDelete').modal('show');

        $('#confirmDelete').off().on('click', function () {
            $.ajax({
                url: `http://localhost:8090/api/v1/sections/${sectionId}`,
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

    // Editar seção
    $(document).on('click', '.btn-edit', function () {
        const sectionId = $(this).data('id');

        // Carregar classificações ao abrir o modal de edição
        $.ajax({
            url: 'http://localhost:8090/api/v1/classifications', // Endereço da API de classificações
            type: 'GET',
            success: function (classifications) {
                // Preencher o campo de classificação
                const classificationSelect = $('#editClassificationId');
                classificationSelect.empty(); // Limpa as opções anteriores
                classificationSelect.append('<option value="">Select Classification</option>'); // Adiciona a opção default

                classifications.forEach(function (classification) {
                    classificationSelect.append(`<option value="${classification.id}">${classification.classificationName}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar as classificações.');
                console.error(error);
            }
        });

        // Carregar dados da seção para edição
        $.ajax({
            url: `http://localhost:8090/api/v1/sections/${sectionId}`, // Endpoint do controlador
            type: 'GET',
            success: function (section) {
                $('#editSectionId').val(section.id);
                $('#editSectionName').val(section.sectionName);
                $('#editSectionDescription').val(section.sectionDescription);
                $('#editClassificationId').val(section.classification.id); // Preencher o campo de classificação com a classificação atual
                $('#modalEditLabel').text('Edit Section');
                $('#modalEdit').modal('show');
            },
            error: function (error) {
                alert('Erro ao carregar os dados da seção.');
                console.error(error);
            }
        });
    });

    // Adicionar nova seção
    $('#btnAddSection').click(function () {
        $.ajax({
            url: 'http://localhost:8090/api/v1/classifications',
            type: 'GET',
            success: function (classifications) {
                const classificationSelect = $('#editClassificationId');
                classificationSelect.empty(); 
                classificationSelect.append('<option value="">Select Classification</option>'); // Adiciona a opção default

                classifications.forEach(function (classification) {
                    classificationSelect.append(`<option value="${classification.id}">${classification.classificationName}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar as classificações.');
                console.error(error);
            }
        });

        $('#editSectionId').val('');
        $('#editSectionName').val('');
        $('#editSectionDescription').val('');
        $('#editClassificationId').val(''); // Limpa a seleção de classificação
        $('#modalEditLabel').text('Add Section');
        $('#modalEdit').modal('show');
    });

    $('#editForm').on('submit', function (e) {
        e.preventDefault();

        const sectionId = $('#editSectionId').val();
        const updatedSection = {
            sectionName: $('#editSectionName').val(),
            sectionDescription: $('#editSectionDescription').val(),
            classificationId: $('#editClassificationId').val() // A ID da classificação
        };

        if (sectionId) {
            $.ajax({
                url: `http://localhost:8090/api/v1/sections/${sectionId}`, // Endpoint do controlador
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(updatedSection),
                success: function () {
                    alert('Success when update');
                    $('#genres').DataTable().ajax.reload();
                    $('#modalEdit').modal('hide');
                },
                error: function (error) {
                    alert('Erro ao atualizar a seção.');
                    console.error(error);
                }
            });
        } else {
            $.ajax({
                url: 'http://localhost:8090/api/v1/sections', // Endpoint do controlador
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(updatedSection),
                success: function () {
                    alert('Success when creating');
                    $('#genres').DataTable().ajax.reload();
                    $('#modalEdit').modal('hide');
                },
                error: function (error) {
                    alert('Erro ao criar a seção.');
                    console.error(error);
                }
            });
        }
    });
});
