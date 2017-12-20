package com.ybj.mesvgdemo;

import android.content.Context;
import android.graphics.Typeface;

import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by 杨阳洋 on 2017/12/20.
 * 自定义
 */

public class CustomFont implements ITypeface {
    private static final String TTF_FILE = "iconfont.ttf";

    private static Typeface typeface = null;

    private static HashMap<String, Character> mChars;


    @Override
    public IIcon getIcon(String key) {
        return Icon.valueOf(key);
    }

    @Override
    public HashMap<String, Character> getCharacters() {
        if (mChars == null) {
            HashMap<String, Character> aChars = new HashMap<String, Character>();
            for (Icon v : Icon.values()) {
                aChars.put(v.name(), v.character);
            }
            mChars = aChars;
        }

        return mChars;
    }

    @Override
    public String getMappingPrefix() {
        return "uat";
    }

    @Override
    public String getFontName() {
        return "CustomFont";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public int getIconCount() {
        return mChars.size();
    }

    @Override
    public Collection<String> getIcons() {
        Collection<String> icons = new LinkedList<String>();

        for (Icon value : Icon.values()) {
            icons.add(value.name());
        }

        return icons;
    }

    @Override
    public String getAuthor() {
        return "uart";
    }

    @Override
    public String getUrl() {
        return "uart2";
    }

    @Override
    public String getDescription() {
        return "android";
    }

    @Override
    public String getLicense() {
        return null;
    }

    @Override
    public String getLicenseUrl() {
        return null;
    }

    @Override
    public Typeface getTypeface(Context ctx) {
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(ctx.getAssets(),TTF_FILE);
            } catch (Exception e) {
                return null;
            }
        }
        return typeface;
    }

    public enum Icon implements IIcon {
        uat_fon_test1('\ue63f'),
        uat_fon_test2('\ue643');

        char character;

        Icon(char character) {
            this.character = character;
        }

        public String getFormattedName() {
            return "{" + name() + "}";
        }

        public char getCharacter() {
            return character;
        }

        public String getName() {
            return name();
        }

        // remember the typeface so we can use it later
        private static ITypeface typeface;

        public ITypeface getTypeface() {
            if (typeface == null) {
                typeface = new CustomFont();
            }
            return typeface;
        }
    }

}
