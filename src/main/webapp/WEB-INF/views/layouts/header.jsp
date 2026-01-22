<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Library system</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/home">Home</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarUserDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        User Manager
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarUserDropdown">
                        <li><a class="dropdown-item" href="/users/create">Add new</a></li>
                        <li><a class="dropdown-item" href="/users">Show List</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarRoleDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Role Manager
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarRoleDropdown">
                        <li><a class="dropdown-item" href="/roles">Show List</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
