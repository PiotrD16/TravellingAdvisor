const API_URL = "http://localhost:8080/api/place/v1";
const CREATE_API_URL = `${API_URL}/savePlace`;
const UPDATE_API_URL = `${API_URL}/updatePlace`;

// === GET ALL PLACES ===
function getAllPlaces() {
    fetch(`${API_URL}/`)
        .then(res => res.json())
        .then(data => {
            const list = document.getElementById("placesList");
            list.innerHTML = "";
            if (!data || data.length === 0) {
                list.innerHTML = "<li>No places found.</li>";
                return;
            }
            data.forEach(place => {
                const li = document.createElement("li");
                li.innerHTML = `
                    <span><b>${place.name}</b> - ${place.opis}</span>
                    <div>
                        <button onclick="editPlace('${place.name}')">Update</button>
                        <button onclick="deletePlace(${place.id_miejsca})">Delete</button>

                    </div>
                `;
                list.appendChild(li);
            });
        })
        .catch(err => {
            document.getElementById("responseMsg").innerText = "Error: " + err.message;
        });
}

// === SHOW FORM ===
function showForm(mode, place = null) {
    const container = document.getElementById("placeFormContainer");
    container.style.display = "block";
    document.getElementById("formTitle").innerText = mode === "add" ? "Add Place" : "Update Place";

    if (mode === "add") {
        document.getElementById("placeId").value = "";
        document.getElementById("placeName").value = "";
        document.getElementById("placeOpis").value = "";
        document.getElementById("countryName").value = "";
        document.getElementById("cityName").value = "";
    } else if (mode === "update" && place) {
        document.getElementById("placeId").value = place.id;
        document.getElementById("placeName").value = place.name;
        document.getElementById("placeOpis").value = place.opis;
        document.getElementById("countryName").value = place.country.country;
        document.getElementById("cityName").value = place.city.city;
    }
}

// === SUBMIT PLACE ===
function submitPlace(event) {
    event.preventDefault();
    const id = document.getElementById("placeId").value;
    const place = {
        placeName: document.getElementById("placeName").value.trim(),
        placeDescription: document.getElementById("placeOpis").value.trim(),
        placeCountry: document.getElementById("countryName").value.trim(),
        placeCity: document.getElementById("cityName").value.trim()
    };

    const url = id ? UPDATE_API_URL : CREATE_API_URL;
    const method = id ? "PUT" : "POST";

    fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(place)
    })
    .then(async res => {
        if (!res.ok) {
            const text = await res.text();
            throw new Error(text || "Failed to save/update place");
        }
        return res.json();
    })
    .then(() => {
        document.getElementById("responseMsg").innerText = id ? "Updated!" : "Added!";
        getAllPlaces();
        showForm("add"); // reset formularza
    })
    .catch(err => {
        let msg = "Error: " + err.message;
        if (msg.includes("City not found")) msg = "Error: City not found in database!";
        if (msg.includes("Country not found")) msg = "Error: Country not found in database!";
        document.getElementById("responseMsg").innerText = msg;
    });
}

// === EDIT PLACE ===
function editPlace(name) {
    fetch(`${API_URL}/byName/${name}`)
        .then(res => res.json())
        .then(place => showForm("update", place))
        .catch(err => {
            document.getElementById("responseMsg").innerText = "Error loading place: " + err.message;
        });
}

// === DELETE PLACE ===
function deletePlace(id) {
    id = Number(id);
    fetch(`${API_URL}/${id}/deletePlace`, { method: "DELETE" })
        .then(res => {
            if (!res.ok) throw new Error("Failed to delete place");
            return res.text();
        })
        .then(msg => {
            document.getElementById("responseMsg").innerText = msg;
            getAllPlaces();
        })
        .catch(err => {
            document.getElementById("responseMsg").innerText = "Error deleting place: " + err.message;
        });
}

// === POKAŻ FORMULARZ DLA MIASTA ===
function showCityForm() {
    document.getElementById("cityFormContainer").style.display = "block";
}

// === SUBMIT CITY ===
function submitCity(event) {
    event.preventDefault();

    const city = {
        cityName: document.getElementById("cityNameInput").value.trim(),
        countryName: document.getElementById("countryNameInput").value.trim()
    };

    fetch("http://localhost:8080/api/city/v1/saveCity", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(city)
    })
    .then(async res => {
        if (!res.ok) {
            const text = await res.text();
            throw new Error(text || "Failed to save city");
        }
        return res.json();
    })
    .then(() => {
        document.getElementById("responseMsg").innerText = "City added successfully!";
        document.getElementById("cityFormContainer").style.display = "none";
        document.getElementById("cityForm").reset();
    })
    .catch(err => {
        let msg = "Error: " + err.message;
        if (msg.includes("City already exists")) msg = "Error: City already exists!";
        if (msg.includes("Country not found")) msg = "Error: Country not found!";
        document.getElementById("responseMsg").innerText = msg;
    });
}