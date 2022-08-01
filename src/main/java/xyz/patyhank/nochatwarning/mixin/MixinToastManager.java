package xyz.patyhank.nochatwarning.mixin;

import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SystemToast.class)
public class MixinToastManager {
    @Shadow
    @Final
    private SystemToast.Type type;

    @Inject(method = "draw", at = @At("HEAD"), cancellable = true)
    public void show(MatrixStack matrices, ToastManager manager, long startTime, CallbackInfoReturnable<Toast.Visibility> cir) {
        if (this.type.equals(SystemToast.Type.UNSECURE_SERVER_WARNING)||this.type.equals(SystemToast.Type.CHAT_PREVIEW_WARNING)) {
            cir.setReturnValue(Toast.Visibility.HIDE);
        }
    }
}
