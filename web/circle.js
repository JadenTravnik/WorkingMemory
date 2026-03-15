/**
 * Circle - represents a single memory circle.
 * Ports LTCircle + MemoryCircle from the original Java application.
 *
 * Behaviour:
 *  - When the mouse moves within the sensitivity radius the circle lights up
 *    green, with brightness proportional to 15/distance (clamped to [0,1]).
 *  - When the mouse leaves the sensitivity radius the circle decays every
 *    DECAY_INTERVAL ms:
 *      brightness >= 0.7  →  brightness *= 0.8
 *      0.05 < brightness < 0.7  →  brightness = brightness²
 *      brightness <= 0.05  →  circle turns purple and stops decaying
 */
class Circle {
    /**
     * @param {number} cx  - centre x coordinate on the canvas
     * @param {number} cy  - centre y coordinate on the canvas
     * @param {number} r   - circle radius in pixels
     * @param {number} sensitivity - extra distance (px) beyond r that the
     *                               mouse can activate the circle
     */
    constructor(cx, cy, r, sensitivity) {
        this.cx = cx;
        this.cy = cy;
        this.r = r;
        this.sR = r + sensitivity; // activation radius
        this.active = true;        // mirrors Java: starts active (black)
        this.brightness = 0;       // HSB brightness, 0-1
        this.isDecayed = false;    // true once faded to purple
    }

    /**
     * Called each time the mouse moves.
     * Mirrors LTCircle#mouseMoved(MouseEvent).
     *
     * @param {number} mouseX
     * @param {number} mouseY
     */
    mouseMoved(mouseX, mouseY) {
        const dx = mouseX - this.cx;
        const dy = mouseY - this.cy;
        let distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < this.sR) {
            this.active = true;
            this.isDecayed = false;
            distance = distance === 0 ? 0.000001 : distance;
            // brightness = 15/distance, clamped to [0, 1]
            this.brightness = Math.min(1.0, 15.0 / distance);
        } else if (this.active) {
            this.active = false;
        }
    }

    /**
     * Applies one decay step.
     * Called by the application's decay timer every DECAY_INTERVAL ms.
     * Mirrors the MemoryCircleUpdateTask in MemoryCircle.java.
     */
    decay() {
        if (this.active) return;

        if (this.brightness > 0.05) {
            if (this.brightness < 0.7) {
                this.brightness *= this.brightness; // fast decay for dim circles
            } else {
                this.brightness *= 0.8;             // slower decay for bright circles
            }
        } else if (!this.isDecayed) {
            this.brightness = 0;
            this.isDecayed = true;
        }
    }

    /**
     * Returns the CSS colour string for the current state.
     * Green (hue 120°) with brightness mapped to HSL lightness,
     * purple (82, 4, 199) when fully decayed, black otherwise.
     */
    _color() {
        if (this.isDecayed) {
            return 'rgb(82, 4, 199)';
        }
        if (this.brightness <= 0) {
            return '#000000';
        }
        // Convert HSB (hue=120°, sat=1, bri=brightness) → CSS HSL.
        // For a fully-saturated HSB colour: HSL_lightness = brightness / 2.
        const l = (this.brightness / 2) * 100;
        return `hsl(120, 100%, ${l.toFixed(2)}%)`;
    }

    /**
     * Draws the circle on the given 2D canvas context.
     * @param {CanvasRenderingContext2D} ctx
     */
    draw(ctx) {
        ctx.beginPath();
        ctx.arc(this.cx, this.cy, this.r, 0, Math.PI * 2);
        ctx.fillStyle = this._color();
        ctx.fill();
    }
}
