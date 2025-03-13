document.addEventListener("DOMContentLoaded", () => {
    const mainToggler = document.getElementById("main-toggler");
    const querySection = document.getElementById("query-section");

    const pageName = window.location.pathname.split("/").pop().split(".")[0];

    mainToggler.addEventListener("click", () => {
        querySection.style.display = querySection.style.display === "none" ? "block" : "none";
    });

    document.querySelectorAll(".toggler-btn").forEach(button => {
        button.addEventListener("click", () => {
            const targetId = button.dataset.type;
            const tabela = document.getElementById(`tabela-${targetId}`);

            tabela.style.display = tabela.style.display === "none" ? "block" : "none";

            if (tabela.dataset.loaded !== "true") {
                fetchData(pageName, targetId, tabela);
            }
        });
    });
});

function fetchData(pageName, type, tabela) {
    fetch(`http://localhost:8090/api/v1/queries/${pageName}?type=${type}`)
        .then(response => response.json())
        .then(data => {
            const tableHead = tabela.querySelector("thead");
            const tableBody = tabela.querySelector("tbody");

            tableHead.innerHTML = "";
            tableBody.innerHTML = "";

            if (data.length > 0) {
                const headers = Object.keys(data[0]);
                let headerRow = "<tr>";
                headers.forEach(header => {
                    headerRow += `<th>${header}</th>`;
                });
                headerRow += "</tr>";
                tableHead.innerHTML = headerRow;

                data.forEach(item => {
                    let row = "<tr>";
                    headers.forEach(header => {
                        let cellData = item[header];

                        // Verifica se o campo é um timestamp e converte para uma data formatada
                        if (
                            typeof cellData === "number" && 
                            !isNaN(new Date(cellData).getTime()) && 
                            new Date(cellData).getFullYear() > 1970
                        ) {
                            const date = new Date(cellData);
                            cellData = date.toLocaleDateString("pt-BR", {
                                day: "2-digit",
                                month: "2-digit",
                                year: "numeric",
                            });
                        }

                        row += `<td>${cellData}</td>`;
                    });
                    row += "</tr>";
                    tableBody.innerHTML += row;
                });
            }
            tabela.dataset.loaded = "true";
        })
        .catch(error => console.error("Erro ao buscar dados:", error));
}
