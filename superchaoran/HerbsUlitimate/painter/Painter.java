package superchaoran.HerbsUlitimate.painter;

import org.powerbot.script.rt6.ClientContext;
import superchaoran.HerbsUlitimate.script.Script;

import java.awt.*;
import java.text.DecimalFormat;

//This is cancer please don't judge me

public class Painter {

    private final String name;
    private final double version;
    private final Script script;
    private final ClientContext ctx;
    private final DecimalFormat formatter = new DecimalFormat("#,###");
    private final Font font = new Font("Arial", Font.PLAIN, 12);
    private final Stroke stroke = new BasicStroke(5);
    private final Stroke stroke2 = new BasicStroke(2);
    private final Color gray = new Color(0, 0, 0, 128);

    public Painter(Script script) {
        this.script = script;
        name = script.getName();
        version = Double.valueOf(script.getManifest().properties().substring(8, 12));
        ctx = (ClientContext) script.context();
    }

    public void drawMouse(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = ctx.input.getLocation().x;
        int y = ctx.input.getLocation().y;
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        g.drawLine(x - 3, y, x + 3, y);
        g.drawLine(x, y - 3, x, y + 3);
        g.setStroke(stroke2);
        g.setColor(Color.WHITE);
        g.drawLine(x - 3, y, x + 3, y);
        g.drawLine(x, y - 3, x, y + 3);
    }

    public void draw(Graphics g, Detail... details) {
        g.setFont(font);
        int height = (details.length + 2) * 14 + 4;
        int width = 107;
        for (Detail d : details) {
            int stringWidth = g.getFontMetrics().stringWidth(content(d));
            if (stringWidth > width)
                width = stringWidth + 5;
        }
        g.setColor(gray);
        g.fillRoundRect(4, 3, width, height, 5, 5);
        g.setColor(Color.WHITE);
        g.drawRoundRect(4, 3, width, height, 5, 5);
        g.drawString(name + " | v" + version, 7, 16);
        g.drawString("Runtime: " + formatTime(script.getRuntime()), 7, 30);
        for (int i = 0; i < details.length; i++) {
            Detail d = details[i];
            g.drawString(content(d), 7, 30 + (14 * (i + 1)));
        }
        drawMouse(g);
    }

    private String content(Detail d) {
        return d.getName() + ": " + format(d.getObject());
    }

    //Especially this
    private String format(Object o) {
        if ((o instanceof Number))
            return formatter.format(o).replace(".", ",");
        return String.valueOf(o);
    }

    public static String formatTime(final long time) {
        final StringBuilder t = new StringBuilder();
        final long total_secs = time / 1000;
        final long total_mins = total_secs / 60;
        final long total_hrs = total_mins / 60;
        final long total_days = total_hrs / 24;
        final int secs = (int) total_secs % 60;
        final int mins = (int) total_mins % 60;
        final int hrs = (int) total_hrs % 24;
        final int days = (int) total_days;
        if (days > 0) {
            if (days < 10) {
                t.append("0");
            }
            t.append(days);
            t.append(":");
        }
        if (hrs < 10) {
            t.append("0");
        }
        t.append(hrs);
        t.append(":");
        if (mins < 10) {
            t.append("0");
        }
        t.append(mins);
        t.append(":");
        if (secs < 10) {
            t.append("0");
        }
        t.append(secs);
        return t.toString();
    }
}
