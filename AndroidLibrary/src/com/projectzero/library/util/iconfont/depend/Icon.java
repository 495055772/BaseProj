/*
 * Copyright (C) 2013 Artur Termenji
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.projectzero.library.util.iconfont.depend;



import java.io.Serializable;

/**
 * An interface which every icon font wrapper should implement.
 */
public interface Icon extends Serializable {

    /**
     * Gets a {@link android.graphics.Typeface} for an Icon.
     * 
     * @return {@link com.projectzero.library.util.iconfont.depend.TypefaceManager.IconicTypeface}
     */
    public TypefaceManager.IconicTypeface getIconicTypeface();

    /**
     * Returns UTF value of an Icon.
     * 
     * @return UTF value of an Icon
     */
    public int getIconUtfValue();

}
