package com.inagata.imageeffects;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.effect.Effect;
import android.media.effect.EffectContext;
import android.media.effect.EffectFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.IntBuffer;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class EffectsFilterActivity extends ActionBarActivity implements GLSurfaceView.Renderer {

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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.effect_factory);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("Effects Factory");
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		/**
		 * Initialise the renderer and tell it to only render when Explicit
		 * requested with the RENDERMODE_WHEN_DIRTY option
		 */
		mEffectView = (GLSurfaceView) findViewById(R.id.effectsview);
		mEffectView.setEGLContextClientVersion(2);
		mEffectView.setRenderer(this);
		mEffectView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		mCurrentEffect = R.id.none;
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

		case R.id.none:
			break;

		case R.id.autofix:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_AUTOFIX);
			mEffect.setParameter("scale", 0.5f);
			break;

		case R.id.bw:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_BLACKWHITE);
			mEffect.setParameter("black", .1f);
			mEffect.setParameter("white", .7f);
			break;

		case R.id.brightness:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_BRIGHTNESS);
			mEffect.setParameter("brightness", 2.0f);
			break;

		case R.id.contrast:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_CONTRAST);
			mEffect.setParameter("contrast", 1.4f);
			break;

		case R.id.crossprocess:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_CROSSPROCESS);
			break;

		case R.id.documentary:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_DOCUMENTARY);
			break;

		case R.id.duotone:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_DUOTONE);
			mEffect.setParameter("first_color", Color.YELLOW);
			mEffect.setParameter("second_color", Color.DKGRAY);
			break;

		case R.id.filllight:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_FILLLIGHT);
			mEffect.setParameter("strength", .8f);
			break;

		case R.id.fisheye:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_FISHEYE);
			mEffect.setParameter("scale", .5f);
			break;

		case R.id.flipvert:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_FLIP);
			mEffect.setParameter("vertical", true);
			break;

		case R.id.fliphor:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_FLIP);
			mEffect.setParameter("horizontal", true);
			break;

		case R.id.grain:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_GRAIN);
			mEffect.setParameter("strength", 1.0f);
			break;

		case R.id.grayscale:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_GRAYSCALE);
			break;

		case R.id.lomoish:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_LOMOISH);
			break;

		case R.id.negative:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_NEGATIVE);
			break;

		case R.id.posterize:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_POSTERIZE);
			break;

		case R.id.rotate:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_ROTATE);
			mEffect.setParameter("angle", 180);
			break;

		case R.id.saturate:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_SATURATE);
			mEffect.setParameter("scale", .5f);
			break;

		case R.id.sepia:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_SEPIA);
			break;

		case R.id.sharpen:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_SHARPEN);
			break;

		case R.id.temperature:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_TEMPERATURE);
			mEffect.setParameter("scale", .9f);
			break;

		case R.id.tint:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_TINT);
			mEffect.setParameter("tint", Color.MAGENTA);
			break;

		case R.id.vignette:
			mEffect = effectFactory.createEffect(EffectFactory.EFFECT_VIGNETTE);
			mEffect.setParameter("scale", .5f);
			break;

			case android.R.id.home:
				mEffect = effectFactory.createEffect(EffectFactory.EFFECT_AUTOFIX);
				mEffect.setParameter("scale", 0.5f);
				finish();
				break;

		default:
			break;

		}
	}

	private void applyEffect() {
		mEffect.apply(mTextures[0], mImageWidth, mImageHeight, mTextures[1]);
	}

	private void renderResult() {
		if (mCurrentEffect != R.id.none) {
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
		if (mCurrentEffect != R.id.none) {
			// if an effect is chosen initialize it and apply it to the texture
			initEffect();
			applyEffect();
		}
		renderResult();
		if (saveFrame) {
			saveBitmap(takeScreenshot(gl));
		}
	}

	private void saveBitmap(Bitmap bitmap) {
		String root = Environment.getExternalStorageDirectory().toString();
	    File myDir = new File(root + "/saved_images");    
	    myDir.mkdirs();
	    Random generator = new Random();
	    int n = 10000;
	    n = generator.nextInt(n);
	    String fname = "Image-"+ n +".jpg";
	    File file = new File (myDir, fname);
	    if (file.exists ()) file.delete (); 
	    try {
	           FileOutputStream out = new FileOutputStream(file);
	           bitmap.compress(CompressFormat.JPEG, 100, out);
	           out.flush();
	           out.close();
	           Log.i("TAG", "Image SAVED=========="+file.getAbsolutePath());
	    } catch (Exception e) {
	           e.printStackTrace();
	    }
		
	}

	public Bitmap takeScreenshot(GL10 mGL) {
		final int mWidth = mEffectView.getWidth();
		final int mHeight = mEffectView.getHeight();
		IntBuffer ib = IntBuffer.allocate(mWidth * mHeight);
		IntBuffer ibt = IntBuffer.allocate(mWidth * mHeight);
		mGL.glReadPixels(0, 0, mWidth, mHeight, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, ib);

		// Convert upside down mirror-reversed image to right-side up normal
		// image.
		for (int i = 0; i < mHeight; i++) {
			for (int j = 0; j < mWidth; j++) {
				ibt.put((mHeight - i - 1) * mWidth + j, ib.get(i * mWidth + j));
			}
		}

		Bitmap mBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
		mBitmap.copyPixelsFromBuffer(ibt);
		return mBitmap;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		setCurrentEffect(item.getItemId());
		mEffectView.requestRender();
		return true;
	}
}
