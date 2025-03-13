$(document).ready(function () {
    // Função para preencher a tabela com os dados dos funcionários
    function populateTable(buttonTemplateCallback) {
        const table = $('#genres').DataTable({
            responsive: true, 
            autoWidth: false, 
            scrollX: true, 
            ajax: {
                url: 'http://localhost:8090/api/v1/employees',  // Endpoint para Employees
                dataSrc: ''
            },
            columns: [
                { data: 'id' },  // ID
                { data: 'employeeName' },  // Name
                { data: 'employeeLastName' },  // Last Name
                { data: 'section.sectionName' },  // Section
                { 
                    data: 'employeeSalary',
                    render: function(data) {
                        return data.toFixed(2);  // Exibe o salário com 2 casas decimais
                    }
                },  // Salary
                { 
                    data: 'employeeBirthdayDate',
                    render: function(data) {
                        // Formatar a data para o formato dd/mm/yyyy
                        const date = new Date(data);
                        const day = ("0" + date.getDate()).slice(-2);  // Garantir dois dígitos
                        const month = ("0" + (date.getMonth() + 1)).slice(-2);  // Garantir dois dígitos
                        const year = date.getFullYear();
                        return `${day}/${month}/${year}`;
                    }
                },  // Birthday Date
                { data: 'address.addressStreet' },  // Street
                { data: 'address.addressNumber' },  // Number
                { data: 'address.addressPostalCode' },  // Postal Code
                { data: 'address.addressCity' },  // City
                { data: 'address.addressCountry' },  // Country
                { 
                    data: null,
                    render: function(data) {
                        return buttonTemplateCallback(data);  // Renderiza os botões de ação
                    }
                }  // Action
            ]
        });

        return table;  // Retorna a referência da tabela para atualizações
    }

    // Carregar lista de sections
    function loadSections() {
        $.ajax({
            url: 'http://localhost:8090/api/v1/sections',  // Endpoint para Sections
            type: 'GET',
            success: function (sections) {
                const sectionSelect = $('#editSectionId');
                sectionSelect.empty();  // Limpar opções existentes

                // Alterar para acessar a chave correta
                sections.forEach(section => {
                    sectionSelect.append(new Option(section.sectionName, section.id));  // Usando section.sectionName em vez de section.name
                });
            },
            error: function (error) {
                alert('Erro ao carregar as sections.');
                console.error(error);
            }
        });
    }

    // Inicializa a tabela
    const table = populateTable(employee => `
        <button type="button" class="btn btn-success btn-edit" data-id="${employee.id}">
            <i class="fa fa-pencil-square-o"></i>
        </button>
        <button type="button" class="btn btn-danger btn-delete" data-id="${employee.id}">
            <i class="fa fa-trash"></i> 
        </button>
    `);

    // Excluir funcionário
    $(document).on('click', '.btn-delete', function () {
        const employeeId = $(this).data('id');
        $('#deleteError').hide();
        $('#modalDeleteLabel').text("Delete employee");
        $('.modal-body-delet').empty().append("<p>Are you sure you want to delete this employee?</p>");
        $('#modalDelete').modal('show');

        $('#confirmDelete').off().on('click', function () {
            $.ajax({
                url: `http://localhost:8090/api/v1/employees/${employeeId}`,
                type: 'DELETE',
                success: function () {
                    $('#modalDelete').modal('hide');
                    $('#genres').DataTable().ajax.reload();
                },
                error: function () {
                    $('#deleteError').text('Error: Failed to delete employee').show();
                }
            });
        });
    });

    // Editar funcionário
    $(document).on('click', '.btn-edit', function () {
        const employeeId = $(this).data('id');

        // Carregar dados do funcionário para edição
        $.ajax({
            url: `http://localhost:8090/api/v1/employees/${employeeId}`,
            type: 'GET',
            success: function (employee) {
                // Preencher os campos do modal com os dados do funcionário
                $('#editEmployeeId').val(employee.id);
                $('#editEmployeeName').val(employee.employeeName);
                $('#editEmployeeLastName').val(employee.employeeLastName);
                $('#editEmployeeSalary').val(employee.employeeSalary);
                $('#editEmployeeBirthdayDate').val(employee.employeeBirthdayDate);
                $('#editAddressStreet').val(employee.address.addressStreet);
                $('#editAddressNumber').val(employee.address.addressNumber);
                $('#editAddressPostalCode').val(employee.address.addressPostalCode);
                $('#editAddressCity').val(employee.address.addressCity);
                $('#editAddressCountry').val(employee.address.addressCountry);
                $('#editSectionId').val(employee.sectionId);  // Seleciona o section correto

                $('#modalEditLabel').text('Edit Employee');
                $('#modalEdit').modal('show');
            },
            error: function (error) {
                alert('Erro ao carregar os dados do funcionário.');
                console.error(error);
            }
        });
    });

    // Abrir modal para adicionar novo funcionário
    $('#btnAddEmployee').click(function () {
        // Limpar campos do formulário
        $('#editEmployeeId').val('');
        $('#editEmployeeName').val('');
        $('#editEmployeeLastName').val('');
        $('#editEmployeeSalary').val('');
        $('#editEmployeeBirthdayDate').val('');
        $('#editAddressStreet').val('');
        $('#editAddressNumber').val('');
        $('#editAddressPostalCode').val('');
        $('#editAddressCity').val('');
        $('#editAddressCountry').val('');
        $('#editSectionId').val('');  // Limpa o campo de section

        $('#modalEditLabel').text('Add Employee');
        $('#modalEdit').modal('show');
    });

    // Salvar as modificações no formulário (Adicionar ou Editar funcionário)
    $('#editForm').on('submit', function (e) {
        e.preventDefault();

        const employeeId = $('#editEmployeeId').val();
        const updatedEmployee = {
            employeeName: $('#editEmployeeName').val(),
            employeeLastName: $('#editEmployeeLastName').val(),
            employeeSalary: $('#editEmployeeSalary').val(),
            employeeBirthdayDate: $('#editEmployeeBirthdayDate').val(),
            addressStreet: $('#editAddressStreet').val(),
            addressNumber: $('#editAddressNumber').val(),
            addressPostalCode: $('#editAddressPostalCode').val(),
            addressCity: $('#editAddressCity').val(),
            addressCountry: $('#editAddressCountry').val(),
            sectionId: $('#editSectionId').val()  // Adiciona o sectionId
        };

        if (employeeId) {
            // Edição do funcionário
            $.ajax({
                url: `http://localhost:8090/api/v1/employees/${employeeId}`,
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(updatedEmployee),
                success: function () {
                    alert('Success when update');
                    table.ajax.reload();  // Atualiza a tabela após edição
                    $('#modalEdit').modal('hide');
                },
                error: function (error) {
                    alert('Erro ao atualizar o funcionário.');
                    console.error(error);
                }
            });
        } else {
            // Adicionar novo funcionário
            $.ajax({
                url: 'http://localhost:8090/api/v1/employees',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(updatedEmployee),
                success: function () {
                    alert('Success when creating');
                    table.ajax.reload();  // Atualiza a tabela após adição
                    $('#modalEdit').modal('hide');
                },
                error: function (error) {
                    alert('Erro ao criar o funcionário.');
                    console.error(error);
                }
            });
        }
    });

    // Carregar as sections ao carregar a página
    loadSections();
});
