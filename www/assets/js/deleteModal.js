var deleteModalHTML = `
<div class="modal-dialog" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="modalDeleteLabel"></h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="modal-body-delet">
            <div id="deleteError" class="alert alert-danger" style="display: none; background-color: #f8d7da; color: #721c24;"></div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-danger" id="confirmDelete">Delete</button>
        </div>
    </div>
</div>
`

$(document).ready(function () {
    $('#modalDelete').html(``);
    $('#modalDelete').html(deleteModalHTML);
});