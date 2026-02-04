const MODES = ["dark", "light", "chaos"];
const ICONS = {
    dark: "🌚​",
    light: "🌞​​", 
    chaos: "😈​"
};

function aplicarTema(modo) {
    const body = document.body;

    body.classList.remove("light", "chaos");

    if (modo === "light") body.classList.add("light");
    if (modo === "chaos") body.classList.add("chaos");

    localStorage.setItem("modo", modo);
    document.getElementById("themeBtn").textContent = ICONS[modo];
}

function cycleMode(){
    let modo = localStorage.getItem("modo") || "dark";
    let next = MODES[(MODES.indexOf(modo) + 1) % MODES.length];
    aplicarTema(next);
};

// restaurar estado
(function() {
    const modo = localStorage.getItem("modo") || "dark";
    aplicarTema(modo);
})();

const SECRET = ["ArrowUp", "ArrowUp", "ArrowDown", "ArrowDown"];
let buffer = [];

window.addEventListener("keydown", (e) => {
    buffer.push(e.key);
    if(buffer.length > SECRET.length) buffer.shift();
    if( SECRET.every((k, i) => k === buffer[i])) {
        activateEasterEgg();
        buffer =[];
    }
});

function activateEasterEgg() {
    document.body.classList.add("chaos");
    const egg = document.createElement("div");
    egg.textContent = "O sistema percebeu você tentando algo secreto! 😈";
    egg.style.position = "fixed";
    egg.style.top = "20px";
    egg.style.right = "20px";
    egg.style.padding = "10px 14px";
    egg.style.background = "#000";
    egg.style.color = "#00ffcc";
    egg.style.borderRadius = "8px";
    egg.style.zIndex = "9999";
    egg.style.boxShadow = "0 0 15px #00ffcc";
    document.body.appendChild(egg);

    setTimeout(() => egg.remove(), 3000);
}

function proximo() {
    fetch("/desafio/novo").then(response => response.json()).then(data => atualizarTela(data));
}

function atualizarTela() {
    document.getElementById("titulo").innerText = desafio.titulo;
    document.getElementById("pergunta").innerText = desafio.pergunta;
}