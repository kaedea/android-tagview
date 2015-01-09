package me.kaede.tagview;

/*
 * Copyright 2014 Namito.S
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.demo_cloud_tagview.R;

/**
 * TagCloudLinkView Class Simple Tagcloud Widget
 */
public class TagView extends RelativeLayout {

	/** tag list */
	private List<Tag> mTags = new ArrayList<Tag>();

	/**
	 * System Service
	 */
	private LayoutInflater mInflater;
	private ViewTreeObserver mViewTreeObserber;

	/**
	 * listener
	 */
	private OnTagClickListener mClickListener;
	private OnTagDeleteListener mDeleteListener;

	/** view size param */
	private int mWidth;

	/**
	 * layout initialize flag
	 */
	private boolean mInitialized = false;

	/**
	 * custom layout param
	 */
	float lineMargin;
	float tagMargin;
	float textMarginLeft;
	float textMarginRight;
	float textMarginTop;
	float textMarginBottom;
	
	
	
	
	/**
	 * constructor
	 * 
	 * @param ctx
	 * @param attrs
	 */
	public TagView(Context ctx) {
		super(ctx, null);
		initialize(ctx, null, 0);
	}

	/**
	 * constructor
	 * 
	 * @param ctx
	 * @param attrs
	 */
	public TagView(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
		initialize(ctx, attrs, 0);
	}

	/**
	 * constructor
	 * 
	 * @param ctx
	 * @param attrs
	 * @param defStyle
	 */
	public TagView(Context ctx, AttributeSet attrs, int defStyle) {
		super(ctx, attrs, defStyle);
		initialize(ctx, attrs, defStyle);
	}

	/**
	 * initalize instance
	 * 
	 * @param ctx
	 * @param attrs
	 * @param defStyle
	 */
	private void initialize(Context ctx, AttributeSet attrs, int defStyle) {
		mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mViewTreeObserber = getViewTreeObserver();
		mViewTreeObserber.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				if (!mInitialized) {
					mInitialized = true;
					drawTags();
				}
			}
		});

		// get AttributeSet
		TypedArray typeArray = ctx.obtainStyledAttributes(attrs, R.styleable.TagView, defStyle, defStyle);
		this.lineMargin =typeArray.getFloat(R.styleable.TagView_lineMargin, Constants.TAG_LAYOUT_BOTTOM_MARGIN);
		this.tagMargin =typeArray.getFloat(R.styleable.TagView_tagMargin, Constants.TAG_LAYOUT_LEFT_MARGIN);
		this.textMarginLeft =typeArray.getFloat(R.styleable.TagView_textMarginLeft, Constants.INNER_VIEW_PADDING_LEFT);
		this.textMarginRight =typeArray.getFloat(R.styleable.TagView_textMarginRight, Constants.INNER_VIEW_PADDING_RIGHT);
		this.textMarginTop =typeArray.getFloat(R.styleable.TagView_textMarginTop, Constants.INNER_VIEW_PADDING_TOP);
		this.textMarginBottom =typeArray.getFloat(R.styleable.TagView_textMarginBottom, Constants.INNER_VIEW_PADDING_BOTTOM);
		typeArray.recycle();
	}




	/**
	 * onSizeChanged
	 * 
	 * @param w
	 * @param h
	 * @param oldw
	 * @param oldh
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mWidth = w;
	}

	

	/**
	 * tag draw
	 */
	@SuppressLint("InflateParams")
	@SuppressWarnings("deprecation")
	private void drawTags() {

		if (!mInitialized) {
			return;
		}

		// clear all tag
		removeAllViews();

		// layout padding left & layout padding right
		float total = getPaddingLeft() + getPaddingRight();
		// 現在位置のindex
		int index = 1;
		// 相対位置起点
		int pindex = index;

		// List Index
		int listIndex = 0;
		for (Tag item : mTags) {
			final int position = listIndex;
			final Tag tag = item;

			// inflate tag layout
			View tagLayout = (View) mInflater.inflate(R.layout.item_tagview, null);
			tagLayout.setId(index);
			tagLayout.setBackgroundDrawable(getSelector(tag));

			// tag text
			TextView tagView = (TextView) tagLayout.findViewById(R.id.tv_tag_item_contain);
			tagView.setText(tag.text);
			tagView.setPadding(Utils.dipToPx(this.getContext(),textMarginLeft), Utils.dipToPx(this.getContext(),textMarginTop), Utils.dipToPx(this.getContext(),textMarginRight), Utils.dipToPx(this.getContext(),textMarginBottom));
			tagView.setTextColor(tag.tagTextColor);
			tagView.setTextSize(Utils.spToPx(getContext(), tag.tagTextSize));
			tagLayout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mClickListener != null) {
						mClickListener.onTagSelected(tag, position);
					}
				}
			});

			// calculate　of tag layout width
			float tagWidth = tagView.getPaint().measureText(tag.text) + Utils.dipToPx(this.getContext(),textMarginLeft) + Utils.dipToPx(this.getContext(),textMarginRight); // tagView
																										// padding
																										// (left
																										// &
																										// right)

			// deletable text
			TextView deletableView = (TextView) tagLayout.findViewById(R.id.tv_tag_item_delete);
			if (tag.isDeletable) {
				deletableView.setVisibility(View.VISIBLE);
				deletableView.setText(tag.deleteIcon);
				deletableView.setPadding(Utils.dipToPx(this.getContext(),textMarginLeft), Utils.dipToPx(this.getContext(),textMarginTop), Utils.dipToPx(this.getContext(),textMarginRight), Utils.dipToPx(this.getContext(),textMarginBottom));
				deletableView.setTextColor(tag.deleteIndicatorColor);
				deletableView.setTextSize(Utils.spToPx(getContext(), tag.deleteIndicatorSize));
				deletableView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						TagView.this.remove(position);
						if (mDeleteListener != null) {
							Tag targetTag = tag;					
							mDeleteListener.onTagDeleted(targetTag, position);
						}
					}
				});
				tagWidth += deletableView.getPaint().measureText(tag.deleteIcon) + Utils.dipToPx(this.getContext(),textMarginLeft) + Utils.dipToPx(this.getContext(),textMarginRight); // deletableView
																														// Padding
																														// (left
																														// &
																														// right)
			} else {
				deletableView.setVisibility(View.GONE);
			}

			LayoutParams tagParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			tagParams.setMargins(0, 0, 0, 0);
			tagParams.bottomMargin = Utils.dipToPx(this.getContext(),lineMargin);
			if (mWidth <= total + tagWidth + Utils.dipToPx(this.getContext(),Constants.LAYOUT_WIDTH_OFFSET)) {
				tagParams.addRule(RelativeLayout.BELOW, pindex);
				// initialize total param (layout padding left & layout padding
				// right)
				total = getPaddingLeft() + getPaddingRight();
				pindex = index;
			} else {
				tagParams.addRule(RelativeLayout.ALIGN_TOP, pindex);
				tagParams.addRule(RelativeLayout.RIGHT_OF, index - 1);
				if (index > 1) {
					tagParams.leftMargin = Utils.dipToPx(this.getContext(),tagMargin);
					total += Utils.dipToPx(this.getContext(),tagMargin);
				}
			}
			total += tagWidth;
			addView(tagLayout, tagParams);
			index++;
			listIndex++;
		}

	}

	

	private StateListDrawable getSelector(Tag tag) {
		StateListDrawable states = new StateListDrawable();
		GradientDrawable gd_normal = new GradientDrawable();
		gd_normal.setColor(tag.layoutColor);
		gd_normal.setCornerRadius(tag.radius);
		GradientDrawable gd_press = new GradientDrawable();
		gd_press.setColor(tag.layoutColorPress);
		gd_press.setCornerRadius(tag.radius);
		states.addState(new int[] { android.R.attr.state_pressed }, gd_press);
		// 必须先添加state_pressed，不然下面的state会覆盖state_pressed的效果
		states.addState(new int[] {}, gd_normal);

		return states;
	}
	
	
	
	
	
	//public methods
	//----------------- separator  -----------------//
	
	/**
	 * add Tag
	 * 
	 * @param tag
	 */
	public void add(Tag tag) {
		mTags.add(tag);
	}

	/**
	 * get tag list
	 * 
	 * @return mTags TagObject List
	 */
	public List<Tag> getTags() {
		return mTags;
	}

	/**
	 * remove tag
	 * 
	 * @param position
	 */
	public void remove(int position) {
		mTags.remove(position);
		drawTags();
	}
	

	/**
	 * setter for OnTagSelectListener
	 * 
	 * @param clickListener
	 */
	public void setOnTagClickListener(OnTagClickListener clickListener) {
		mClickListener = clickListener;
	}

	/**
	 * setter for OnTagDeleteListener
	 * 
	 * @param deleteListener
	 */
	public void setOnTagDeleteListener(OnTagDeleteListener deleteListener) {
		mDeleteListener = deleteListener;
	}

}
