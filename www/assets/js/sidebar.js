$(document).ready(function () {


    var sidebarHTML = `
    <nav class="custom-sidebar d-flex flex-column p-3" style="width: 250px; height: 100vh; position: fixed; top: 0; left: 0; background-color: #343a40;">
       
    
        <a class="navbar-brand d-flex align-items-center mb-4" href="index.html">
            <img src="assets/images/LogoBranca.png" alt="BlueVelvet Logo" style="width: 150px;">
        </a>
    
        <ul class="navbar-nav flex-column">
            <li class="nav-item">
                <a class="nav-link text-white" href="actors.html">Actors</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="directors.html">Directors</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="genres.html">Genres</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="movies.html">Movies</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="borrowings.html">Borrowings</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="clients.html">Clients</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="employees.html">Employees</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="exemplars.html">Exemplars</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="sections.html">Sections</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="classifications.html">Classifications</a>
            </li>
        </ul>
    </nav>`;

    $('#sideBar').html(sidebarHTML);

});
