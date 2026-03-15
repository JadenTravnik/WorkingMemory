/**
 * app.js – main application entry point.
 * Ports WorkingMemory.java: creates and manages 1000 LTCircle instances on
 * a canvas, forwards mouse-move events, and runs the decay timer.
 */

const NUM_CIRCLES   = 1000;
const WIDTH         = 1200;
const HEIGHT        = 800;
const RADIUS        = 10;
const SENSITIVITY   = 100;
const DECAY_INTERVAL = 250; // ms — matches Java Timer period

const canvas = document.getElementById('canvas');
const ctx    = canvas.getContext('2d');
canvas.width  = WIDTH;
canvas.height = HEIGHT;

// --- Build the circle collection (mirrors initLongTerm in WorkingMemory.java) ---
const circles = [];
for (let i = 0; i < NUM_CIRCLES; i++) {
    // Keep centres at least one radius away from each edge so circles stay
    // fully on-screen (mirrors Java: rand.nextInt(width-radius)+radius).
    const cx = Math.random() * (WIDTH  - 2 * RADIUS) + RADIUS;
    const cy = Math.random() * (HEIGHT - 2 * RADIUS) + RADIUS;
    circles.push(new Circle(cx, cy, RADIUS, SENSITIVITY));
}

// --- Mouse tracking (mirrors WorkingMemory#mouseMoved) ---
canvas.addEventListener('mousemove', (e) => {
    const rect = canvas.getBoundingClientRect();
    // Scale mouse coords to canvas logical size in case CSS scales the element.
    const scaleX = WIDTH  / rect.width;
    const scaleY = HEIGHT / rect.height;
    const mouseX = (e.clientX - rect.left) * scaleX;
    const mouseY = (e.clientY - rect.top)  * scaleY;

    for (const circle of circles) {
        circle.mouseMoved(mouseX, mouseY);
    }
});

// --- Decay timer (mirrors Java Timer at 250 ms period) ---
setInterval(() => {
    for (const circle of circles) {
        circle.decay();
    }
}, DECAY_INTERVAL);

// --- Render loop ---
function render() {
    // Clear with black background
    ctx.fillStyle = '#000000';
    ctx.fillRect(0, 0, WIDTH, HEIGHT);

    for (const circle of circles) {
        circle.draw(ctx);
    }

    requestAnimationFrame(render);
}

render();
