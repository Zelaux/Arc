package arc.fx.filters;

import arc.*;
import arc.fx.*;
import arc.fx.util.*;
import arc.graphics.*;

public final class MixFilter extends FxFilter{
    private Texture inputTexture2 = null;
    public float mix = 0.5f;

    public MixFilter(){
        super(compileShader(
        Core.files.classpath("shaders/screenspace.vert"),
        Core.files.classpath("shaders/mix.frag")));

        rebind();
    }

    public MixFilter setInput(FxBuffer buffer1, FxBuffer buffer2){
        this.inputTexture = buffer1.getFbo().getTexture();
        this.inputTexture2 = buffer2.getFbo().getTexture();
        return this;
    }

    public MixFilter setInput(Texture texture1, Texture texture2){
        this.inputTexture = texture1;
        this.inputTexture2 = texture2;
        return this;
    }

    /** @deprecated use {@link #setInput(FxBuffer, FxBuffer)} instead. */
    @Override
    public MixFilter setInput(FxBuffer input){
        throw new UnsupportedOperationException("Use #setInput(FboWrapper, FboWrapper)} instead.");
    }

    /** @deprecated use {@link #setInput(Texture, Texture)} instead. */
    @Override
    public MixFilter setInput(Texture input){
        throw new UnsupportedOperationException("Use #setInput(Texture, Texture)} instead.");
    }

    @Override
    public void resize(int width, int height){

    }

    @Override
    public void setParams(){
        shader.setUniformi("u_texture0", u_texture0);
        shader.setUniformi("u_texture1", u_texture1);
        shader.setUniformf("u_mix", mix);
    }

    @Override
    protected void onBeforeRender(){
        inputTexture.bind(u_texture0);
        inputTexture2.bind(u_texture1);
    }
}
