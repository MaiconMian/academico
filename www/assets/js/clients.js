function populateTable(buttonTemplateCallback) {
    const table = $('#genres').DataTable({
        responsive: true, 
        autoWidth: false, 
        scrollX: true, 
        ajax: {
            url: 'http://localhost:8090/api/v1/clients', 
            dataSrc: ''  // A resposta será um array de objetos
        },
        columns: [
            { data: 'id' },  // ID do cliente
            { data: 'clientName' },  // Nome do cliente
            { data: 'clientLastName' },  // Sobrenome do cliente
            { data: 'address.addressStreet' },  // Rua do endereço
            { data: 'address.addressNumber' },  // Número do endereço
            { data: 'address.addressPostalCode' },  // Código postal
            { data: 'address.addressCity' },  // Cidade
            { data: 'address.addressCountry' },  // País
            {
                data: null,
                render: function(data) {
                    return buttonTemplateCallback(data);  // Renderiza o botão de ação
                }
            }
        ]
    });
}

$(document).ready(function () {
    populateTable(client => `
        <button type="button" class="btn btn-success btn-edit" data-id="${client.id}">
            <i class="fa fa-pencil-square-o"></i> 
        </button>
        <button type="button" class="btn btn-danger btn-delete" data-id="${client.id}">
            <i class="fa fa-trash"></i>
        </button>
    `);

    // Excluir client
    $(document).on('click', '.btn-delete', function () {
        const clientId = $(this).data('id');
        $('#deleteError').hide();
        $('#modalDeleteLabel').text("Delete client");
        $('.modal-body-delet').empty().append("<p>Are you sure you want to delete this client?</p>");
        $('#modalDelete').modal('show');

        $('#confirmDelete').off().on('click', function () {
            $.ajax({
                url: `http://localhost:8090/api/v1/clients/${clientId}`,
                type: 'DELETE',
                success: function () {
                    $('#modalDelete').modal('hide');
                    $('#genres').DataTable().ajax.reload();
                },
                error: function () {
                    $('#deleteError').text('Error: Failed to delete client').show();
                }
            });
        });
    });

    // Editar client
    $(document).on('click', '.btn-edit', function () {
        const clientId = $(this).data('id');

        // Carregar dados do client para edição
        $.ajax({
            url: `http://localhost:8090/api/v1/clients/${clientId}`, 
            type: 'GET',
            success: function (client) {
                $('#editClientId').val(client.id);
                $('#editClientName').val(client.clientName);
                $('#editClientLastName').val(client.clientLastName);
                $('#editAddressStreet').val(client.address.addressStreet);
                $('#editAddressNumber').val(client.address.addressNumber);
                $('#editAddressPostalCode').val(client.address.addressPostalCode);
                $('#editAddressCity').val(client.address.addressCity);
                $('#editAddressCountry').val(client.address.addressCountry);
                $('#modalEditLabel').text('Edit Client');
                $('#modalEdit').modal('show');
            },
            error: function (error) {
                alert('Erro ao carregar os dados do cliente.');
                console.error(error);
            }
        });
    });

    // Adicionar novo client
    $('#btnAddClient').click(function () {
        $('#editClientId').val('');
        $('#editClientName').val('');
        $('#editClientLastName').val('');
        $('#editAddressStreet').val('');
        $('#editAddressNumber').val('');
        $('#editAddressPostalCode').val('');
        $('#editAddressCity').val('');
        $('#editAddressCountry').val('');
        $('#modalEditLabel').text('Add Client');
        $('#modalEdit').modal('show');
    });

    // Salvar as modificações no formulário (Adicionar ou Editar cliente)
    $('#editForm').on('submit', function (e) {
        e.preventDefault();

        const clientId = $('#editClientId').val();
        const updatedClient = {
            clientName: $('#editClientName').val(),
            clientLastName: $('#editClientLastName').val(),
            addressStreet: $('#editAddressStreet').val(),
            addressNumber: $('#editAddressNumber').val(),
            addressPostalCode: $('#editAddressPostalCode').val(),
            addressCity: $('#editAddressCity').val(),
            addressCountry: $('#editAddressCountry').val()
        };

        if (clientId) {
            // Edição do cliente
            $.ajax({
                url: `http://localhost:8090/api/v1/clients/${clientId}`, 
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(updatedClient),
                success: function () {
                    alert('Success when update');
                    // Atualiza a tabela após a edição
                    $('#genres').DataTable().ajax.reload();
                    $('#modalEdit').modal('hide');
                },
                error: function (error) {
                    alert('Erro ao atualizar o cliente.');
                    console.error(error);
                }
            });
        } else {
            // Adição de novo cliente
            $.ajax({
                url: 'http://localhost:8090/api/v1/clients', 
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(updatedClient),
                success: function () {
                    alert('Success when creating');
                    // Atualiza a tabela após a criação
                    $('#genres').DataTable().ajax.reload();
                    $('#modalEdit').modal('hide');
                },
                error: function (error) {
                    alert('Erro ao criar o cliente.');
                    console.error(error);
                }
            });
        }
    });
});
