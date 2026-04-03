const BASE_URL = "http://localhost:8080/api/finance";

// LOGIN
function login() {
    const role = document.getElementById("role").value;

    localStorage.setItem("role", role); // ✅ MUST EXIST

    window.location.href = "index.html";
}


// LOAD
window.onload = function() {
    const user = localStorage.getItem("user");
    const role = localStorage.getItem("role");

    if (document.getElementById("userInfo")) {
        document.getElementById("userInfo").innerText = user + " (" + role + ")";
    }

    if (role !== "ADMIN") {
        const btn = document.getElementById("addBtn");
        if (btn) btn.style.display = "none";
    }

    if (document.getElementById("income")) {
        loadSummary();
        loadRecords(); // ✅ THIS WAS MISSING
    }
};



// LOAD SUMMARY
function loadSummary() {
    fetch(BASE_URL + "/summary")
    .then(res => res.json())
    .then(data => {
        document.getElementById("income").innerText = data.income || 0;
        document.getElementById("expense").innerText = data.expense || 0;
        document.getElementById("balance").innerText = data.balance || 0;
    });
}

// NAVIGATION
function goToAdd() {
    window.location.href = "add-record.html";
}

// ADD RECORD
function addRecord() {
    const role = localStorage.getItem("role");

    const data = {
        amount: parseFloat(document.getElementById("amount").value),
        type: document.getElementById("type").value,
        category: document.getElementById("category").value,
        recordDate: document.getElementById("date").value,
        description: document.getElementById("desc").value
    };

    fetch(BASE_URL + "/create", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "role": role
        },
        body: JSON.stringify(data)
    })
    .then(res => {
        if (!res.ok) throw new Error("Error: " + res.status);
        return res.json();
    })
    .then(() => {
        document.getElementById("msg").innerText = "✅ Record Added!";
    })
    .catch(err => {
        document.getElementById("msg").innerText = err.message;
    });
}



function loadRecords() {
    const role = localStorage.getItem("role");

    if (!role) {
        console.error("Role not found in localStorage");
        return;
    }

    fetch("http://localhost:8080/api/finance/all", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "role": role
        }
    })
    .then(res => {
        console.log("Response Status:", res.status);
        if (!res.ok) {
            throw new Error("HTTP ERROR: " + res.status);
        }
        return res.json();
    })
    .then(data => {
        console.log("DATA RECEIVED:", data);

        const tbody = document.querySelector("#table tbody");

        if (!tbody) {
            console.error("Table body not found!");
            return;
        }

        tbody.innerHTML = "";

        data.forEach(r => {
            const row = `
            <tr>
                <td>${r.id}</td>
                <td>${r.amount}</td>
                <td>${r.type}</td>
                <td>${r.category}</td>
                <td>${r.recordDate}</td>
                <td>
                    <button onclick="updateRecord(${r.id})">Update</button>
                    <button onclick="deleteRecord(${r.id})">Delete</button>
                </td>
            </tr>`;
            tbody.innerHTML += row;
        });
    })
    .catch(err => {
        console.error("ERROR:", err);
    });
}



function deleteRecord(id) {
    const role = localStorage.getItem("role");

    fetch(BASE_URL + "/delete/" + id, {
        method: "DELETE",
        headers: { "role": role }
    })
    .then(() => {
        loadRecords();
        loadSummary();
    });
}


function updateRecord(id) {
    const newAmount = prompt("Enter new amount:");

    fetch(BASE_URL + "/update/" + id, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "role": localStorage.getItem("role")
        },
        body: JSON.stringify({
            amount: parseFloat(newAmount),
            type: "INCOME",
            category: "Updated",
            recordDate: "2026-04-03",
            description: "Updated"
        })
    })
    .then(() => {
        loadRecords();
        loadSummary();
    });
}