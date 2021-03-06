package binnie.core.machines.power;

import binnie.core.machines.IMachine;
import net.minecraft.nbt.NBTTagCompound;

public abstract class ComponentProcess extends ComponentProcessIndefinate implements IProcessTimed {
    private float progressAmount;

    public ComponentProcess(final IMachine machine) {
        super(machine, 0.0f);
        this.progressAmount = 0.0f;
    }

    @Override
    public float getEnergyPerTick() {
        return (float) this.getProcessEnergy() / (float) this.getProcessLength();
    }

    @Override
    public float getProgressPerTick() {
        return 100.0f / (float) this.getProcessLength();
    }

    @Override
    protected void onStartTask() {
        this.progressAmount += 0.01f;
    }

    @Override
    protected void onCancelTask() {
        this.progressAmount = 0.0f;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.progressAmount >= 100.0f) {
            this.onFinishTask();
            this.progressAmount = 0.0f;
        }
    }

    public void alterProgress(final float f) {
        this.progressAmount += f;
    }

    @Override
    protected void progressTick() {
        super.progressTick();
        this.alterProgress(this.getProgressPerTick());
    }

    public boolean inProgress() {
        return this.progressAmount > 0.0f;
    }

    @Override
    public float getProgress() {
        return this.progressAmount;
    }

    public void setProgress(final float f) {
        this.progressAmount = f;
    }

    protected void onFinishTask() {
    }

    @Override
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.progressAmount = nbt.getFloat("progress");
    }

    @Override
    public void writeToNBT(final NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setFloat("progress", this.progressAmount);
    }

    @Override
    public abstract int getProcessLength();

    @Override
    public abstract int getProcessEnergy();
}
