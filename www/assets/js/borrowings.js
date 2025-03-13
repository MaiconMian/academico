function populateTable(buttonTemplateCallback) {
    const table = $('#genres').DataTable({
        responsive: true, 
        autoWidth: false, 
        scrollX: true, 
        ajax: {
            url: 'http://localhost:8090/api/v1/borrowings',
            dataSrc: ''
        },
        columns: [
            { data: 'id' },
            { data: 'client.clientName' },
            { data: 'employee.employeeName' },
            { data: 'borrowingIsPaid' },
            {
                data: 'borrowingSaleDate',
                render: function(data) {
                    return data ? new Date(data).toLocaleDateString('pt-BR') : 'N/A';
                }
            },
            {
                data: 'borrowingDevolutionDate',
                render: function(data) {
                    return data ? new Date(data).toLocaleDateString('pt-BR') : 'N/A';
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
    populateTable(borrowing => `
        <button type="button" class="btn btn-success btn-edit" data-id="${borrowing.id}">
            <i class="fa fa-pencil-square-o"></i> 
        </button>
        <button type="button" class="btn btn-danger btn-delete" data-id="${borrowing.id}">
            <i class="fa fa-trash"></i> 
        </button>
    `);

    $(document).on('click', '.btn-delete', function () {
        const borrowingId = $(this).data('id');
        $('#deleteError').hide();
        $('#modalDeleteLabel').text("Delete borrowing");
        $('.modal-body-delet').empty().append("<p>Are you sure you want to delete this borrowing?</p>");
        $('#modalDelete').modal('show');

        $('#confirmDelete').off().on('click', function () {
            $.ajax({
                url: `http://localhost:8090/api/v1/borrowings/${borrowingId}`,
                type: 'DELETE',
                success: function () {
                    $('#modalDelete').modal('hide');
                    $('#genres').DataTable().ajax.reload();
                },
                error: function () {
                    $('#deleteError').text('Error: Failed to delete borrowing').show();
                }
            });
        });
    });

    $(document).on('click', '.btn-edit', function () {
        const borrowingId = $(this).data('id');
        $.ajax({
            url: `http://localhost:8090/api/v1/borrowings/${borrowingId}`,
            type: 'GET',
            success: function (borrowing) {
                $('#editBorrowingId').val(borrowing.id);
                $('#editClientId').val(borrowing.client?.id);
                $('#editEmployeeId').val(borrowing.employee?.id);
                $('#editStartDate').val(borrowing.borrowingSaleDate ? new Date(borrowing.borrowingSaleDate).toISOString().split('T')[0] : '');
                $('#editReturnDate').val(borrowing.borrowingDevolutionDate ? new Date(borrowing.borrowingDevolutionDate).toISOString().split('T')[0] : '');
                $('#editIsPaid').prop('checked', borrowing.borrowingIsPaid);
                loadEditForm(borrowing);
                $('#modalEditLabel').text('Edit Borrowing');
                $('#modalEdit').modal('show');
            },
            error: function (error) {
                alert('Erro ao carregar os dados do empréstimo.');
                console.error(error);
            }
        });
    });

    $('#btnAddBorrowing').click(function () {
        $('#editBorrowingId').val('');
        $('#editClientId').val('');
        $('#editExemplarId').val('');
        $('#editEmployeeId').val('');
        $('#editStartDate').val('');
        $('#editReturnDate').val('');
        $('#editIsPaid').prop('checked', false);
        loadAddForm();
        $('#modalEditLabel').text('Add Borrowing');
        $('#modalEdit').modal('show');
    });

    function loadAddForm() {
        $.ajax({
            url: 'http://localhost:8090/api/v1/clients',
            type: 'GET',
            success: function (clients) {
                const clientSelect = $('#editClientId');
                clientSelect.empty().append('<option value="">Select Client</option>');
                clients.forEach(client => {
                    clientSelect.append(`<option value="${client.id}">${client.clientName || client.name}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar os clientes.');
                console.error(error);
            }
        });

        $.ajax({
            url: 'http://localhost:8090/api/v1/exemplars',
            type: 'GET',
            success: function (exemplars) {
                const exemplarSelect = $('#editExemplarId');
                exemplars.forEach(exemplar => {
                    exemplarSelect.append(`<option value="${exemplar.id}">${exemplar.movie.movieName || exemplar.name}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar os exemplares.');
                console.error(error);
            }
        });

        $.ajax({
            url: 'http://localhost:8090/api/v1/employees',
            type: 'GET',
            success: function (employees) {
                const employeeSelect = $('#editEmployeeId');
                employeeSelect.empty().append('<option value="">Select Employee</option>');
                employees.forEach(employee => {
                    employeeSelect.append(`<option value="${employee.id}">${employee.employeeName || employee.name}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar os funcionários.');
                console.error(error);
            }
        });
    }

    function loadEditForm(borrowing) {
        $.ajax({
            url: 'http://localhost:8090/api/v1/clients',
            type: 'GET',
            success: function (clients) {
                const clientSelect = $('#editClientId');
                clients.forEach(client => {
                    const option = new Option(client.clientName, client.id, client.id == borrowing.client.id, client.id == borrowing.client.id);
                    clientSelect.append(option);
                });
            },
            error: function (error) {
                alert('Erro ao carregar os clientes.');
                console.error(error);
            }
        });
    
        $.ajax({
            url: 'http://localhost:8090/api/v1/employees',
            type: 'GET',
            success: function (employees) {
                const employeeSelect = $('#editEmployeeId');
                employees.forEach(employee => {
                    const option = new Option(employee.employeeName, employee.id, employee.id == borrowing.employee.id, employee.id == borrowing.employee.id);
                    employeeSelect.append(option);
                });
            },
            error: function (error) {
                alert('Erro ao carregar os funcionários.');
                console.error(error);
            }
        });
    
        $.ajax({
            url: 'http://localhost:8090/api/v1/exemplars',
            type: 'GET',
            success: function (exemplars) {
                const exemplarSelect = $('#editExemplarId');
                exemplars.forEach(exemplar => {
                    exemplarSelect.append(`<option value="${exemplar.id}">${exemplar.movie.movieName || exemplar.name}</option>`);
                });
            },
            error: function (error) {
                alert('Erro ao carregar os exemplares.');
                console.error(error);
            }
        });
    }
    

    $('#editForm').on('submit', function (e) {
        e.preventDefault();
        const borrowingId = $('#editBorrowingId').val();

        const updatedBorrowing = {
            clientId: $('#editClientId').val(),
            employeeId: $('#editEmployeeId').val(),
            exemplarIds: $('#editExemplarId').val(),
            borrowingSaleDate: $('#editStartDate').val(),
            borrowingDevolutionDate: $('#editReturnDate').val(),
            borrowingIsPaid: $('#editIsPaid').prop('checked')
        };

        const method = borrowingId ? 'PUT' : 'POST';
        const url = borrowingId
            ? `http://localhost:8090/api/v1/borrowings/${borrowingId}`
            : 'http://localhost:8090/api/v1/borrowings';

        $.ajax({
            url: url,
            type: method,
            data: JSON.stringify(updatedBorrowing),
            contentType: 'application/json',
            success: function () {
                alert('Success when creating');
                $('#modalEdit').modal('hide');
                $('#genres').DataTable().ajax.reload();
            },
            error: function (error) {
                alert('Erro ao salvar o empréstimo.');
                console.error(error);
            }
        });
    });
});
