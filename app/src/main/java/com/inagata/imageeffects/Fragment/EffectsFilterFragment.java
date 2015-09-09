package com.inagata.imageeffects.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.effect.Effect;
import android.media.effect.EffectContext;
import android.media.effect.EffectFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inagata.imageeffects.Adapter.FilterAdapterFactory;
import com.inagata.imageeffects.Utilities.GLToolbox;
import com.inagata.imageeffects.R;
import com.inagata.imageeffects.Utilities.RecyclerItemClickListener;
import com.inagata.imageeffects.Utilities.TextureRenderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class EffectsFilterFragment extends Fragment implements GLSurfaceView.Renderer {

	private RecyclerView recList;
	int mCurrentEffect;
	private GLSurfaceView mEffectView;
	private int[] mTextures = new int[2];
	private EffectContext mEffectContext;
	private Effect mEffect;
	private TextureRenderer mTexRenderer = new TextureRenderer();
	private int mImageWidth;
	private int mImageHeight;
	private boolean mInitialized = false;
	private volatile boolean saveFrame;

	public void setCurrentEffect(int effect) {
		mCurrentEffect = effect;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.imf_effect_factory, container, false);
		mEffectView = (GLSurfaceView) rootView.findViewById(R.id.effectsview);
		mEffectView.setEGLContextClientVersion(2);
		mEffectView.setRenderer(this);
		mEffectView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		mCurrentEffect = 0;

		LinearLayoutManager layoutManager
				= new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

		recList = (RecyclerView) rootView.findViewById(R.id.rc_filter);
		recList.setHasFixedSize(true);
		recList.setLayoutManager(layoutManager);

		FilterAdapterFactory filterAdapter = new FilterAdapterFactory(getActivity());
		recList.setAdapter(filterAdapter);

        recList.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                setCurrentEffect(position);
                mEffectView.requestRender();
            }
        }));

		return rootView;
	}

	private void loadTextures() {
		// Generate textures
		GLES20.glGenTextures(2, mTextures, 0);

		// Load input bitmap
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nabilah);
		mImageWidth = bitmap.getWidth();
		mImageHeight = bitmap.getHeight();
		mTexRenderer.updateTextureSize(mImageWidth, mImageHeight);

		// Upload to texture
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextures[0]);
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

		// Set texture parameters
		GLToolbox.initTexParams();
		bitmap.recycle();
	}

	private void initEffect() {
		EffectFactory effectFactory = mEffectContext.getFactory();
		if (mEffect != null) {
			mEffect.release();
		}
		/**
		 * Initialize the correct effect based on the selected menu/action item
		 */
		switch (mCurrentEffect) {

		case 0:
			break;

		case 1:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_AUTOFIX);
			mEffect.setParameter("scale", 0.5f);
			break;

		case 2:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_BLACKWHITE);
			mEffect.setParameter("black", .1f);
			mEffect.setParameter("white", .7f);
			break;

		case 3:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_BRIGHTNESS);
			mEffect.setParameter("brightness", 2.0f);
			break;

		case 4:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_CONTRAST);
			mEffect.setParameter("contrast", 1.4f);
			break;

		case 5:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_CROSSPROCESS);
			break;

		case 6:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_DOCUMENTARY);
			break;

		case 7:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_DUOTONE);
			mEffect.setParameter("first_color", Color.YELLOW);
			mEffect.setParameter("second_color", Color.DKGRAY);
			break;

		case 8:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_FILLLIGHT);
			mEffect.setParameter("strength", .8f);
			break;

		case 9:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_FISHEYE);
			mEffect.setParameter("scale", .5f);
			break;

		case 10:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_FLIP);
			mEffect.setParameter("vertical", true);
			break;

		case 11:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_FLIP);
			mEffect.setParameter("horizontal", true);
			break;

		case 12:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_GRAIN);
			mEffect.setParameter("strength", 1.0f);
			break;

		case 13:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_GRAYSCALE);
			break;

		case 14:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_LOMOISH);
			break;

		case 15:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_NEGATIVE);
			break;

		case 16:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_POSTERIZE);
			break;

		case 17:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_ROTATE);
			mEffect.setParameter("angle", 180);
			break;

		case 18:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_SATURATE);
			mEffect.setParameter("scale", .5f);
			break;

		case 19:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_SEPIA);
			break;

		case 20:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_SHARPEN);
			break;

		case 21:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_TEMPERATURE);
			mEffect.setParameter("scale", .9f);
			break;

		case 22:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_TINT);
			mEffect.setParameter("tint", Color.MAGENTA);
			break;

		case 23:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_VIGNETTE);
			mEffect.setParameter("scale", .5f);
			break;

		default:
			break;

		}
	}

	private void applyEffect() {
		mEffect.apply(mTextures[0], mImageWidth, mImageHeight, mTextures[1]);
	}

	private void renderResult() {
		if (mCurrentEffect != 0) {
			// if no effect is chosen, just render the original bitmap
			mTexRenderer.renderTexture(mTextures[1]);
		} else {
			saveFrame=true;
			// render the result of applyEffect()
			mTexRenderer.renderTexture(mTextures[0]);
		}
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		if (!mInitialized) {
			// Only need to do this once
			mEffectContext = EffectContext.createWithCurrentGlContext();
			mTexRenderer.init();
			loadTextures();
			mInitialized = true;
		}
		if (mCurrentEffect != 0) {
			// if an effect is chosen initialize it and apply it to the texture
			initEffect();
			applyEffect();
		}
		renderResult();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if (mTexRenderer != null) {
			mTexRenderer.updateViewSize(width, height);
		}
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
	}
}
