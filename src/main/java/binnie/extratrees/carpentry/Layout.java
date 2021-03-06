package binnie.extratrees.carpentry;

import binnie.extratrees.api.IDesignSystem;
import binnie.extratrees.api.ILayout;
import binnie.extratrees.api.IPattern;
import net.minecraft.util.IIcon;

public class Layout implements ILayout {
    IPattern pattern;
    boolean inverted;

    private Layout(final IPattern pattern, final boolean inverted) {
        this.pattern = pattern;
        this.inverted = inverted;
    }

    private Layout(final IPattern pattern) {
        this(pattern, false);
    }

    public static ILayout get(final IPattern pattern, final boolean inverted) {
        return new Layout(pattern, inverted);
    }

    public static ILayout get(final IPattern pattern) {
        return new Layout(pattern, false);
    }

    @Override
    public IPattern getPattern() {
        return this.pattern;
    }

    @Override
    public boolean isInverted() {
        return this.inverted;
    }

    ILayout newLayout(final ILayout newLayout) {
        return new Layout(newLayout.getPattern(), this.inverted ^ newLayout.isInverted());
    }

    @Override
    public ILayout rotateRight() {
        return this.rotateLeft().rotateLeft().rotateLeft();
    }

    @Override
    public ILayout rotateLeft() {
        return this.newLayout(this.pattern.getRotation());
    }

    @Override
    public ILayout flipHorizontal() {
        return this.newLayout(this.pattern.getHorizontalFlip());
    }

    @Override
    public ILayout flipVertical() {
        return this.newLayout(this.pattern.getHorizontalFlip().rotateLeft().rotateLeft());
    }

    @Override
    public IIcon getPrimaryIcon(final IDesignSystem system) {
        return this.inverted ? this.pattern.getSecondaryIcon(system) : this.pattern.getPrimaryIcon(system);
    }

    @Override
    public IIcon getSecondaryIcon(final IDesignSystem system) {
        return this.inverted ? this.pattern.getPrimaryIcon(system) : this.pattern.getSecondaryIcon(system);
    }

    @Override
    public ILayout invert() {
        return new Layout(this.pattern, !this.inverted);
    }
}
