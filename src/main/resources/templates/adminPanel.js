let mainServices = [];
let subServices = [];
let experts = [];
fetchMainServices();
fetchSubServices();
fetchExperts();

function showForm(formId) {
    document.querySelectorAll('.container').forEach(container => {
        container.style.display = 'none';

    });
    document.getElementById(formId).style.display = 'block';

}

function logout() {
    window.location.href = '/logout';

}

function toggleText() {
    const buttons = document.querySelectorAll('.control-panel-button');
    const sidebar = document.getElementById('sidebar');
    buttons.forEach(button => {
        button.classList.toggle('hide-text');
    });
    sidebar.classList.toggle('collapsed');
}

async function fetchMainServices() {
    try {
        const response = await fetch("/admin/allMainService");
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        let data = await response.json();
        mainServices = data.map(service => ({
            id: service.id,
            name: service.name
        }));
        const mainserviceSelect = document.getElementById('mainservice-select');
        mainserviceSelect.innerHTML = '';

        data.forEach(service => {
            const option = document.createElement('option');
            option.value = service.id;
            option.textContent = service.name;
            mainserviceSelect.appendChild(option);
        });
        const mainServiceTableBody = document.getElementById('mainservice-table-body');
        mainServiceTableBody.innerHTML = '';
        mainServices.forEach(service => {
            const row = document.createElement('tr');
            row.innerHTML = `
            <td>${service.name}</td>
            <td><button class="remove-button" onclick="removeMainService(${service.id})">Remove</button></td>
        `;
            mainServiceTableBody.appendChild(row);
        });
    } catch (error) {
        console.error('There has been a problem with your fetch operation:', error);
    }
}

function removeMainService(serviceId) {
    fetch("/admin/remove/mainService/".concat(serviceId), {method: "POST"})
        .then(response => {
            if (!response.ok) {
                alert("Can't remove a main service with sub services attacked!")
                throw new Error('Network response was not ok');
            }
        }).then(_ => {
        mainServices = [];
        fetchMainServices()
    })
}

function registerMainService() {
    const name = document.getElementById('mainservice-name').value;
    fetch('../admin/register/mainService', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({name: name}),
    }).then(response => {
        if (!response.ok) {
            alert("This mainService has already been registered!")
            throw new Error('Network response was not ok');
        }
        mainServices = [];
        fetchMainServices()
    })
}

function registerSubService() {
    const name = document.getElementById('subservice-name').value;
    const mainServiceId = document.getElementById('mainservice-select').value;
    const basePrice = document.getElementById('subservice-price').value;
    const comment = document.getElementById('subservice-comment').value;
    fetch('../admin/register/subService', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({name: name,
            mainServiceId: mainServiceId,
            basePrice: basePrice,
            comment: comment}),
    }).then(response => {
        if (!response.ok) {
            alert("This subService has already been registered!")
            throw new Error('Network response was not ok');
        }
    })
}

async function fetchSubServices() {
    const response = await fetch("/services/subService/loadALl");
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    let data = await response.json();
    subServices = data.map(service => ({
        id: service.id,
        name: service.name,
        basePrice: service.basePrice,
        comment: service.comment,
        mainServiceName: service.mainServiceName
    }))
    const subServiceTableBody = document.getElementById('subService-table-body');
    subServiceTableBody.innerHTML = '';
    subServices.forEach(service => {
        const row = document.createElement('tr');
        row.innerHTML = `
                    <td>${service.name}</td>
                    <td>${service.basePrice}</td>
                    <td>${service.mainServiceName}</td>
                    <td>${service.comment}</td>
                    <td><button class="remove-button" onclick="removeSubService(${service.id})">Remove</button></td>
                    `;
        subServiceTableBody.appendChild(row);
    });
}

function removeSubService(serviceId){
    fetch("/admin/remove/subService/".concat(serviceId), {method: "POST"})
        .then(response => {
            if (!response.ok) {
                alert("This Sub Service Has Orders Attached!")
                throw new Error('Network response was not ok');
            }
        }).then(_ => {
        subServices = [];
        fetchSubServices()
    })
}

async function fetchExperts() {
    const response = await fetch("/admin/expert/loadNotConfirm");
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    let data = await response.json();
    experts = data.map(service => ({
        id: service.id,
        firstname: service.firstname,
        lastname: service.lastname,
        username: service.username,
        email: service.email
    }))
    const expertTableBody = document.getElementById('expert-table-body');
    expertTableBody.innerHTML = '';
    experts.forEach(service => {
        const row = document.createElement('tr');
        row.innerHTML = `
                    <td>${service.firstname}</td>
                    <td>${service.lastname}</td>
                    <td>${service.username}</td>
                    <td>${service.email}</td>
                    <td>${service.username}</td>
                    <td><button class="remove-button" onclick="confirmExpert(${service.id})">Confirm</button></td>
                    `;
        expertTableBody.appendChild(row);
    });
}

function confirmExpert() {

}