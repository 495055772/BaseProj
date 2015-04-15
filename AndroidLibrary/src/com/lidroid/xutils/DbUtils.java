/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * <h2>
<a name="dbutils%E4%BD%BF%E7%94%A8%E6%96%B9%E6%B3%95" class="anchor" href="#dbutils%E4%BD%BF%E7%94%A8%E6%96%B9%E6%B3%95"><span class="octicon octicon-link"></span></a>DbUtils使用方法：</h2>

<div class="highlight highlight-java"><pre><span class="n">DbUtils</span> <span class="n">db</span> <span class="o">=</span> <span class="n">DbUtils</span><span class="o">.</span><span class="na">create</span><span class="o">(</span><span class="k">this</span><span class="o">);</span>
<span class="n">User</span> <span class="n">user</span> <span class="o">=</span> <span class="k">new</span> <span class="n">User</span><span class="o">();</span> <span class="c1">//这里需要注意的是User对象必须有id属性，或者有通过@ID注解的属性</span>
<span class="n">user</span><span class="o">.</span><span class="na">setEmail</span><span class="o">(</span><span class="s">"wyouflf@qq.com"</span><span class="o">);</span>
<span class="n">user</span><span class="o">.</span><span class="na">setName</span><span class="o">(</span><span class="s">"wyouflf"</span><span class="o">);</span>
<span class="n">db</span><span class="o">.</span><span class="na">save</span><span class="o">(</span><span class="n">user</span><span class="o">);</span> <span class="c1">// 使用saveBindingId保存实体时会为实体的id赋值</span>

<span class="o">...</span>
<span class="c1">// 查找</span>
<span class="n">Parent</span> <span class="n">entity</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findById</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">,</span> <span class="n">parent</span><span class="o">.</span><span class="na">getId</span><span class="o">());</span>
<span class="n">Parent</span> <span class="n">entity</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findFirst</span><span class="o">(</span><span class="n">entity</span><span class="o">);</span><span class="c1">//通过entity的属性查找</span>
<span class="n">List</span><span class="o">&lt;</span><span class="n">Parent</span><span class="o">&gt;</span> <span class="n">list</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findAll</span><span class="o">(</span><span class="n">entity</span><span class="o">);</span><span class="c1">//通过entity的属性查找</span>
<span class="n">List</span><span class="o">&lt;</span><span class="n">Parent</span><span class="o">&gt;</span> <span class="n">list</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findAll</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">);</span><span class="c1">//通过类型查找</span>

<span class="n">Parent</span> <span class="n">Parent</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findFirst</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">).</span><span class="na">where</span><span class="o">(</span><span class="s">"name"</span><span class="o">,</span><span class="s">"="</span><span class="o">,</span><span class="s">"test"</span><span class="o">));</span>

<span class="c1">// IS NULL</span>
<span class="n">Parent</span> <span class="n">Parent</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findFirst</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">).</span><span class="na">where</span><span class="o">(</span><span class="s">"name"</span><span class="o">,</span><span class="s">"="</span><span class="o">,</span> <span class="kc">null</span><span class="o">));</span>
<span class="c1">// IS NOT NULL</span>
<span class="n">Parent</span> <span class="n">Parent</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findFirst</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">).</span><span class="na">where</span><span class="o">(</span><span class="s">"name"</span><span class="o">,</span><span class="s">"!="</span><span class="o">,</span> <span class="kc">null</span><span class="o">));</span>

<span class="c1">// WHERE id&lt;54 AND (age&gt;20 OR age&lt;30) ORDER BY id LIMIT pageSize OFFSET pageOffset</span>
<span class="n">List</span><span class="o">&lt;</span><span class="n">Parent</span><span class="o">&gt;</span> <span class="n">list</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findAll</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">)</span>
                                   <span class="o">.</span><span class="na">where</span><span class="o">(</span><span class="s">"id"</span> <span class="o">,</span><span class="s">"&lt;"</span><span class="o">,</span> <span class="mi">54</span><span class="o">)</span>
                                   <span class="o">.</span><span class="na">and</span><span class="o">(</span><span class="n">WhereBuilder</span><span class="o">.</span><span class="na">b</span><span class="o">(</span><span class="s">"age"</span><span class="o">,</span> <span class="s">"&gt;"</span><span class="o">,</span> <span class="mi">20</span><span class="o">).</span><span class="na">or</span><span class="o">(</span><span class="s">"age"</span><span class="o">,</span> <span class="s">" &lt; "</span><span class="o">,</span> <span class="mi">30</span><span class="o">))</span>
                                   <span class="o">.</span><span class="na">orderBy</span><span class="o">(</span><span class="s">"id"</span><span class="o">)</span>
                                   <span class="o">.</span><span class="na">limit</span><span class="o">(</span><span class="n">pageSize</span><span class="o">)</span>
                                   <span class="o">.</span><span class="na">offset</span><span class="o">(</span><span class="n">pageSize</span> <span class="o">*</span> <span class="n">pageIndex</span><span class="o">));</span>

<span class="c1">// op为"in"时，最后一个参数必须是数组或Iterable的实现类(例如List等)</span>
<span class="n">Parent</span> <span class="n">test</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findFirst</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">).</span><span class="na">where</span><span class="o">(</span><span class="s">"id"</span><span class="o">,</span> <span class="s">"in"</span><span class="o">,</span> <span class="k">new</span> <span class="kt">int</span><span class="o">[]{</span><span class="mi">1</span><span class="o">,</span> <span class="mi">2</span><span class="o">,</span> <span class="mi">3</span><span class="o">}));</span>
<span class="c1">// op为"between"时，最后一个参数必须是数组或Iterable的实现类(例如List等)</span>
<span class="n">Parent</span> <span class="n">test</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findFirst</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">).</span><span class="na">where</span><span class="o">(</span><span class="s">"id"</span><span class="o">,</span> <span class="s">"between"</span><span class="o">,</span> <span class="k">new</span> <span class="n">String</span><span class="o">[]{</span><span class="s">"1"</span><span class="o">,</span> <span class="s">"5"</span><span class="o">}));</span>

<span class="n">DbModel</span> <span class="n">dbModel</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findDbModelAll</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">).</span><span class="na">select</span><span class="o">(</span><span class="s">"name"</span><span class="o">));</span><span class="c1">//select("name")只取出name列</span>
<span class="n">List</span><span class="o">&lt;</span><span class="n">DbModel</span><span class="o">&gt;</span> <span class="n">dbModels</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findDbModelAll</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">).</span><span class="na">groupBy</span><span class="o">(</span><span class="s">"name"</span><span class="o">).</span><span class="na">select</span><span class="o">(</span><span class="s">"name"</span><span class="o">,</span> <span class="s">"count(name)"</span><span class="o">));</span>
<span class="o">...</span>

<span class="n">List</span><span class="o">&lt;</span><span class="n">DbModel</span><span class="o">&gt;</span> <span class="n">dbModels</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findDbModelAll</span><span class="o">(</span><span class="n">sql</span><span class="o">);</span> <span class="c1">// 自定义sql查询</span>
<span class="n">db</span><span class="o">.</span><span class="na">execNonQuery</span><span class="o">(</span><span class="n">sql</span><span class="o">)</span> <span class="c1">// 执行自定义sql</span>
<span class="o">...</span>

</pre></div>
 * 
 */

package com.lidroid.xutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.lidroid.xutils.db.sqlite.*;
import com.lidroid.xutils.db.table.*;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.IOUtils;
import com.lidroid.xutils.util.LogUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * https://github.com/wyouflf/xUtils<br>
 * <h2>xUtils简介</h2><br>
 * xUtils 最初源于Afinal框架，进行了大量重构，拥有更加灵活的ORM，更多的事件注解支持且不受混淆影响...<br>
 * xUitls最低兼容android 2.2 (api level 8)<br>
 * <br>
 * <hr>
 * 
 * <h2>混淆时注意事项：</h2><br>
 * 添加Android默认混淆配置${sdk.dir}/tools/proguard/proguard-android.txt<br>
 * 不要混淆xUtils中的注解类型，添加混淆配置：-keep class * extends java.lang.annotation.Annotation { *; }<br>
 * 对使用DbUtils模块持久化的实体类不要混淆，或者注解所有表和列名称@Table(name="xxx")，@Id(column="xxx")，@Column(column="xxx"),@Foreign(column="xxx",foreign="xxx")；<br>
 * <hr>
 * 
 * <h2>DbUtils使用方法：</h2>
 * <div class="highlight highlight-java">
 * 
 * <pre>
 * <span class="n">DbUtils</span> <span class="n">db</span> <span class="o">=</span> <span class="n">DbUtils</span><span class="o">.</span><span class="na">create</span><span class="o">(</span><span class="k">this</span><span class="o">);</span>
 * <span class="n">User</span> <span class="n">user</span> <span class="o">=</span> <span class="k">new</span> <span class="n">User</span><span class="o">();</span> <span class="c1">//这里需要注意的是User对象必须有id属性，或者有通过@ID注解的属性</span>
 * <span class="n">user</span><span class="o">.</span><span class="na">setEmail</span><span class="o">(</span><span class="s">"wyouflf@qq.com"</span><span class="o">);</span>
 * <span class="n">user</span><span class="o">.</span><span class="na">setName</span><span class="o">(</span><span class="s">"wyouflf"</span><span class="o">);</span>
 * <span class="n">db</span><span class="o">.</span><span class="na">save</span><span class="o">(</span><span class="n">user</span><span class="o">);</span> <span class="c1">// 使用saveBindingId保存实体时会为实体的id赋值</span>
 * 
 * <span class="o">...</span>
 * <span class="c1">// 查找</span>
 * <span class="n">Parent</span> <span class="n">entity</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findById</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">,</span> <span class="n">parent</span><span class="o">.</span><span class="na">getId</span><span class="o">());</span>
 * <span class="n">Parent</span> <span class="n">entity</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findFirst</span><span class="o">(</span><span class="n">entity</span><span class="o">);</span><span class="c1">//通过entity的属性查找</span>
 * <span class="n">List</span><span class="o">&lt;</span><span class="n">Parent</span><span class="o">&gt;</span> <span class="n">list</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findAll</span><span class="o">(</span><span class="n">entity</span><span class="o">);</span><span class="c1">//通过entity的属性查找</span>
 * <span class="n">List</span><span class="o">&lt;</span><span class="n">Parent</span><span class="o">&gt;</span> <span class="n">list</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findAll</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">);</span><span class="c1">//通过类型查找</span>
 * 
 * <span class="n">Parent</span> <span class="n">Parent</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findFirst</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">).</span><span class="na">where</span><span class="o">(</span><span class="s">"name"</span><span class="o">,</span><span class="s">"="</span><span class="o">,</span><span class="s">"test"</span><span class="o">));</span>
 * 
 * <span class="c1">// IS NULL</span>
 * <span class="n">Parent</span> <span class="n">Parent</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findFirst</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">).</span><span class="na">where</span><span class="o">(</span><span class="s">"name"</span><span class="o">,</span><span class="s">"="</span><span class="o">,</span> <span class="kc">null</span><span class="o">));</span>
 * <span class="c1">// IS NOT NULL</span>
 * <span class="n">Parent</span> <span class="n">Parent</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findFirst</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">).</span><span class="na">where</span><span class="o">(</span><span class="s">"name"</span><span class="o">,</span><span class="s">"!="</span><span class="o">,</span> <span class="kc">null</span><span class="o">));</span>
 * 
 * <span class="c1">// WHERE id&lt;54 AND (age&gt;20 OR age&lt;30) ORDER BY id LIMIT pageSize OFFSET pageOffset</span>
 * <span class="n">List</span><span class="o">&lt;</span><span class="n">Parent</span><span class="o">&gt;</span> <span class="n">list</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findAll</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">)</span>
 *                                    <span class="o">.</span><span class="na">where</span><span class="o">(</span><span class="s">"id"</span> <span class="o">,</span><span class="s">"&lt;"</span><span class="o">,</span> <span class="mi">54</span><span class="o">)</span>
 *                                    <span class="o">.</span><span class="na">and</span><span class="o">(</span><span class="n">WhereBuilder</span><span class="o">.</span><span class="na">b</span><span class="o">(</span><span class="s">"age"</span><span class="o">,</span> <span class="s">"&gt;"</span><span class="o">,</span> <span class="mi">20</span><span class="o">).</span><span class="na">or</span><span class="o">(</span><span class="s">"age"</span><span class="o">,</span> <span class="s">" &lt; "</span><span class="o">,</span> <span class="mi">30</span><span class="o">))</span>
 *                                    <span class="o">.</span><span class="na">orderBy</span><span class="o">(</span><span class="s">"id"</span><span class="o">)</span>
 *                                    <span class="o">.</span><span class="na">limit</span><span class="o">(</span><span class="n">pageSize</span><span class="o">)</span>
 *                                    <span class="o">.</span><span class="na">offset</span><span class="o">(</span><span class="n">pageSize</span> <span class="o">*</span> <span class="n">pageIndex</span><span class="o">));</span>
 * 
 * <span class="c1">// op为"in"时，最后一个参数必须是数组或Iterable的实现类(例如List等)</span>
 * <span class="n">Parent</span> <span class="n">test</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findFirst</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">).</span><span class="na">where</span><span class="o">(</span><span class="s">"id"</span><span class="o">,</span> <span class="s">"in"</span><span class="o">,</span> <span class="k">new</span> <span class="kt">int</span><span class="o">[]{</span><span class="mi">1</span><span class="o">,</span> <span class="mi">2</span><span class="o">,</span> <span class="mi">3</span><span class="o">}));</span>
 * <span class="c1">// op为"between"时，最后一个参数必须是数组或Iterable的实现类(例如List等)</span>
 * <span class="n">Parent</span> <span class="n">test</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findFirst</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">).</span><span class="na">where</span><span class="o">(</span><span class="s">"id"</span><span class="o">,</span> <span class="s">"between"</span><span class="o">,</span> <span class="k">new</span> <span class="n">String</span><span class="o">[]{</span><span class="s">"1"</span><span class="o">,</span> <span class="s">"5"</span><span class="o">}));</span>
 * 
 * <span class="n">DbModel</span> <span class="n">dbModel</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findDbModelAll</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">).</span><span class="na">select</span><span class="o">(</span><span class="s">"name"</span><span class="o">));</span><span class="c1">//select("name")只取出name列</span>
 * <span class="n">List</span><span class="o">&lt;</span><span class="n">DbModel</span><span class="o">&gt;</span> <span class="n">dbModels</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findDbModelAll</span><span class="o">(</span><span class="n">Selector</span><span class="o">.</span><span class="na">from</span><span class="o">(</span><span class="n">Parent</span><span class="o">.</span><span class="na">class</span><span class="o">).</span><span class="na">groupBy</span><span class="o">(</span><span class="s">"name"</span><span class="o">).</span><span class="na">select</span><span class="o">(</span><span class="s">"name"</span><span class="o">,</span> <span class="s">"count(name)"</span><span class="o">));</span>
 * <span class="o">...</span>
 * 
 * <span class="n">List</span><span class="o">&lt;</span><span class="n">DbModel</span><span class="o">&gt;</span> <span class="n">dbModels</span> <span class="o">=</span> <span class="n">db</span><span class="o">.</span><span class="na">findDbModelAll</span><span class="o">(</span><span class="n">sql</span><span class="o">);</span> <span class="c1">// 自定义sql查询</span>
 * <span class="n">db</span><span class="o">.</span><span class="na">execNonQuery</span><span class="o">(</span><span class="n">sql</span><span class="o">)</span> <span class="c1">// 执行自定义sql</span>
 * <span class="o">...</span>
 * </pre>
 * 
 * </div>
 */
public class DbUtils {

    // *************************************** create instance ****************************************************

    /**
     * key: dbName
     */
    private static HashMap<String, DbUtils> daoMap           = new HashMap<String, DbUtils>();

    private SQLiteDatabase                  database;
    private DaoConfig                       daoConfig;
    private boolean                         debug            = false;
    private boolean                         allowTransaction = false;

    private DbUtils(DaoConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("daoConfig may not be null");
        }
        if (config.getContext() == null) {
            throw new IllegalArgumentException("context mey not be null");
        }
        this.database = createDatabase(config);
        this.daoConfig = config;
    }

    private synchronized static DbUtils getInstance(DaoConfig daoConfig) {
        DbUtils dao = daoMap.get(daoConfig.getDbName());
        if (dao == null) {
            dao = new DbUtils(daoConfig);
            daoMap.put(daoConfig.getDbName(), dao);
        } else {
            dao.daoConfig = daoConfig;
        }

        // update the database if needed
        SQLiteDatabase database = dao.database;
        int oldVersion = database.getVersion();
        int newVersion = daoConfig.getDbVersion();
        if (oldVersion != newVersion) {
            if (oldVersion != 0) {
                DbUpgradeListener upgradeListener = daoConfig.getDbUpgradeListener();
                if (upgradeListener != null) {
                    upgradeListener.onUpgrade(dao, oldVersion, newVersion);
                } else {
                    try {
                        dao.dropDb();
                    } catch (DbException e) {
                        LogUtils.e(e.getMessage(), e);
                    }
                }
            }
            database.setVersion(newVersion);
        }

        return dao;
    }

    public static DbUtils create(Context context) {
        DaoConfig config = new DaoConfig(context);
        return getInstance(config);
    }

    public static DbUtils create(Context context, String dbName) {
        DaoConfig config = new DaoConfig(context);
        config.setDbName(dbName);
        return getInstance(config);
    }

    public static DbUtils create(Context context, String dbDir, String dbName) {
        DaoConfig config = new DaoConfig(context);
        config.setDbDir(dbDir);
        config.setDbName(dbName);
        return getInstance(config);
    }

    public static DbUtils create(Context context, String dbName, int dbVersion, DbUpgradeListener dbUpgradeListener) {
        DaoConfig config = new DaoConfig(context);
        config.setDbName(dbName);
        config.setDbVersion(dbVersion);
        config.setDbUpgradeListener(dbUpgradeListener);
        return getInstance(config);
    }

    public static DbUtils create(Context context, String dbDir, String dbName, int dbVersion,
            DbUpgradeListener dbUpgradeListener) {
        DaoConfig config = new DaoConfig(context);
        config.setDbDir(dbDir);
        config.setDbName(dbName);
        config.setDbVersion(dbVersion);
        config.setDbUpgradeListener(dbUpgradeListener);
        return getInstance(config);
    }

    public static DbUtils create(DaoConfig daoConfig) {
        return getInstance(daoConfig);
    }

    public DbUtils configDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public DbUtils configAllowTransaction(boolean allowTransaction) {
        this.allowTransaction = allowTransaction;
        return this;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public DaoConfig getDaoConfig() {
        return daoConfig;
    }

    // *********************************************** operations ********************************************************

    public void saveOrUpdate(Object entity) throws DbException {
        try {
            beginTransaction();

            createTableIfNotExist(entity.getClass());
            saveOrUpdateWithoutTransaction(entity);

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void saveOrUpdateAll(List<?> entities) throws DbException {
        if (entities == null || entities.size() == 0)
            return;
        try {
            beginTransaction();

            createTableIfNotExist(entities.get(0).getClass());
            for (Object entity : entities) {
                saveOrUpdateWithoutTransaction(entity);
            }

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void replace(Object entity) throws DbException {
        try {
            beginTransaction();

            createTableIfNotExist(entity.getClass());
            execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(this, entity));

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void replaceAll(List<?> entities) throws DbException {
        if (entities == null || entities.size() == 0)
            return;
        try {
            beginTransaction();

            createTableIfNotExist(entities.get(0).getClass());
            for (Object entity : entities) {
                execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(this, entity));
            }

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void save(Object entity) throws DbException {
        try {
            beginTransaction();

            createTableIfNotExist(entity.getClass());
            execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(this, entity));

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void saveAll(List<?> entities) throws DbException {
        if (entities == null || entities.size() == 0)
            return;
        try {
            beginTransaction();

            createTableIfNotExist(entities.get(0).getClass());
            for (Object entity : entities) {
                execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(this, entity));
            }

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public boolean saveBindingId(Object entity) throws DbException {
        boolean result = false;
        try {
            beginTransaction();

            createTableIfNotExist(entity.getClass());
            result = saveBindingIdWithoutTransaction(entity);

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
        return result;
    }

    public void saveBindingIdAll(List<?> entities) throws DbException {
        if (entities == null || entities.size() == 0)
            return;
        try {
            beginTransaction();

            createTableIfNotExist(entities.get(0).getClass());
            for (Object entity : entities) {
                if (!saveBindingIdWithoutTransaction(entity)) {
                    throw new DbException("saveBindingId error, transaction will not commit!");
                }
            }

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void deleteById(Class<?> entityType, Object idValue) throws DbException {
        if (!tableIsExist(entityType))
            return;
        try {
            beginTransaction();

            execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(entityType, idValue));

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void delete(Object entity) throws DbException {
        if (!tableIsExist(entity.getClass()))
            return;
        try {
            beginTransaction();

            execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(entity));

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void delete(Class<?> entityType, WhereBuilder whereBuilder) throws DbException {
        if (!tableIsExist(entityType))
            return;
        try {
            beginTransaction();

            execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(entityType, whereBuilder));

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void deleteAll(List<?> entities) throws DbException {
        if (entities == null || entities.size() == 0 || !tableIsExist(entities.get(0).getClass()))
            return;
        try {
            beginTransaction();

            for (Object entity : entities) {
                execNonQuery(SqlInfoBuilder.buildDeleteSqlInfo(entity));
            }

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void deleteAll(Class<?> entityType) throws DbException {
        delete(entityType, null);
    }

    public void update(Object entity, String... updateColumnNames) throws DbException {
        if (!tableIsExist(entity.getClass()))
            return;
        try {
            beginTransaction();

            execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, entity, updateColumnNames));

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void update(Object entity, WhereBuilder whereBuilder, String... updateColumnNames) throws DbException {
        if (!tableIsExist(entity.getClass()))
            return;
        try {
            beginTransaction();

            execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, entity, whereBuilder, updateColumnNames));

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void updateAll(List<?> entities, String... updateColumnNames) throws DbException {
        if (entities == null || entities.size() == 0 || !tableIsExist(entities.get(0).getClass()))
            return;
        try {
            beginTransaction();

            for (Object entity : entities) {
                execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, entity, updateColumnNames));
            }

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public void updateAll(List<?> entities, WhereBuilder whereBuilder, String... updateColumnNames) throws DbException {
        if (entities == null || entities.size() == 0 || !tableIsExist(entities.get(0).getClass()))
            return;
        try {
            beginTransaction();

            for (Object entity : entities) {
                execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, entity, whereBuilder, updateColumnNames));
            }

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T findById(Class<T> entityType, Object idValue) throws DbException {
        if (!tableIsExist(entityType))
            return null;

        Id id = TableUtils.getId(entityType);
        Selector selector = Selector.from(entityType).where(id.getColumnName(), "=", idValue);

        String sql = selector.limit(1).toString();
        long seq = CursorUtils.FindCacheSequence.getSeq();
        findTempCache.setSeq(seq);
        Object obj = findTempCache.get(sql);
        if (obj != null) {
            return (T) obj;
        }

        Cursor cursor = execQuery(sql);
        try {
            if (cursor.moveToNext()) {
                T entity = (T) CursorUtils.getEntity(this, cursor, entityType, seq);
                findTempCache.put(sql, entity);
                return entity;
            }
        } finally {
            IOUtils.closeQuietly(cursor);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T findFirst(Selector selector) throws DbException {
        if (!tableIsExist(selector.getEntityType()))
            return null;

        String sql = selector.limit(1).toString();
        long seq = CursorUtils.FindCacheSequence.getSeq();
        findTempCache.setSeq(seq);
        Object obj = findTempCache.get(sql);
        if (obj != null) {
            return (T) obj;
        }

        Cursor cursor = execQuery(sql);
        try {
            if (cursor.moveToNext()) {
                T entity = (T) CursorUtils.getEntity(this, cursor, selector.getEntityType(), seq);
                findTempCache.put(sql, entity);
                return entity;
            }
        } finally {
            IOUtils.closeQuietly(cursor);
        }
        return null;
    }

    public <T> T findFirst(Class<T> entityType) throws DbException {
        return findFirst(Selector.from(entityType));
    }

    public <T> T findFirst(Class<T> entityType, WhereBuilder whereBuilder) throws DbException {
        return findFirst(Selector.from(entityType).where(whereBuilder));
    }

    public <T> T findFirst(Object entity) throws DbException {
        if (!tableIsExist(entity.getClass()))
            return null;

        Selector selector = Selector.from(entity.getClass());
        List<KeyValue> entityKvList = SqlInfoBuilder.entity2KeyValueList(this, entity);
        if (entityKvList != null) {
            WhereBuilder wb = WhereBuilder.b();
            for (KeyValue keyValue : entityKvList) {
                Object value = keyValue.getValue();
                if (value != null) {
                    wb.and(keyValue.getKey(), "=", value);
                }
            }
            selector.where(wb);
        }
        return findFirst(selector);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> findAll(Selector selector) throws DbException {
        if (!tableIsExist(selector.getEntityType()))
            return null;

        String sql = selector.toString();
        long seq = CursorUtils.FindCacheSequence.getSeq();
        findTempCache.setSeq(seq);
        Object obj = findTempCache.get(sql);
        if (obj != null) {
            return (List<T>) obj;
        }

        Cursor cursor = execQuery(sql);
        List<T> result = new ArrayList<T>();
        try {
            while (cursor.moveToNext()) {
                T entity = (T) CursorUtils.getEntity(this, cursor, selector.getEntityType(), seq);
                result.add(entity);
            }
            findTempCache.put(sql, result);
        } finally {
            IOUtils.closeQuietly(cursor);
        }
        return result;
    }

    public <T> List<T> findAll(Class<T> entityType) throws DbException {
        return findAll(Selector.from(entityType));
    }

    public <T> List<T> findAll(Class<T> entityType, WhereBuilder whereBuilder) throws DbException {
        return findAll(Selector.from(entityType).where(whereBuilder));
    }

    public <T> List<T> findAll(Object entity) throws DbException {
        if (!tableIsExist(entity.getClass()))
            return null;

        Selector selector = Selector.from(entity.getClass());
        List<KeyValue> entityKvList = SqlInfoBuilder.entity2KeyValueList(this, entity);
        if (entityKvList != null) {
            WhereBuilder wb = WhereBuilder.b();
            for (KeyValue keyValue : entityKvList) {
                Object value = keyValue.getValue();
                if (value != null) {
                    wb.and(keyValue.getKey(), "=", value);
                }
            }
            selector.where(wb);
        }
        return findAll(selector);
    }

    public DbModel findDbModelFirst(SqlInfo sqlInfo) throws DbException {
        Cursor cursor = execQuery(sqlInfo);
        try {
            if (cursor.moveToNext()) {
                return CursorUtils.getDbModel(cursor);
            }
        } finally {
            IOUtils.closeQuietly(cursor);
        }
        return null;
    }

    public DbModel findDbModelFirst(DbModelSelector selector) throws DbException {
        if (!tableIsExist(selector.getEntityType()))
            return null;

        Cursor cursor = execQuery(selector.limit(1).toString());
        try {
            if (cursor.moveToNext()) {
                return CursorUtils.getDbModel(cursor);
            }
        } finally {
            IOUtils.closeQuietly(cursor);
        }
        return null;
    }

    public List<DbModel> findDbModelAll(SqlInfo sqlInfo) throws DbException {
        Cursor cursor = execQuery(sqlInfo);
        List<DbModel> dbModelList = new ArrayList<DbModel>();
        try {
            while (cursor.moveToNext()) {
                dbModelList.add(CursorUtils.getDbModel(cursor));
            }
        } finally {
            IOUtils.closeQuietly(cursor);
        }
        return dbModelList;
    }

    public List<DbModel> findDbModelAll(DbModelSelector selector) throws DbException {
        if (!tableIsExist(selector.getEntityType()))
            return null;

        Cursor cursor = execQuery(selector.toString());
        List<DbModel> dbModelList = new ArrayList<DbModel>();
        try {
            while (cursor.moveToNext()) {
                dbModelList.add(CursorUtils.getDbModel(cursor));
            }
        } finally {
            IOUtils.closeQuietly(cursor);
        }
        return dbModelList;
    }

    public long count(Selector selector) throws DbException {
        Class<?> entityType = selector.getEntityType();
        if (!tableIsExist(entityType))
            return 0;

        DbModelSelector dmSelector = selector.select("count(" + TableUtils.getId(entityType).getColumnName()
                + ") as count");
        return findDbModelFirst(dmSelector).getLong("count");
    }

    public long count(Class<?> entityType) throws DbException {
        return count(Selector.from(entityType));
    }

    public long count(Class<?> entityType, WhereBuilder whereBuilder) throws DbException {
        return count(Selector.from(entityType).where(whereBuilder));
    }

    public long count(Object entity) throws DbException {
        if (!tableIsExist(entity.getClass()))
            return 0;

        Selector selector = Selector.from(entity.getClass());
        List<KeyValue> entityKvList = SqlInfoBuilder.entity2KeyValueList(this, entity);
        if (entityKvList != null) {
            WhereBuilder wb = WhereBuilder.b();
            for (KeyValue keyValue : entityKvList) {
                Object value = keyValue.getValue();
                if (value != null) {
                    wb.and(keyValue.getKey(), "=", value);
                }
            }
            selector.where(wb);
        }
        return count(selector);
    }

    // ******************************************** config ******************************************************

    public static class DaoConfig {
        private Context           context;
        private String            dbName    = "xUtils.db"; // default db name
        private int               dbVersion = 1;
        private DbUpgradeListener dbUpgradeListener;

        private String            dbDir;

        public DaoConfig(Context context) {
            this.context = context;
        }

        public Context getContext() {
            return context;
        }

        public String getDbName() {
            return dbName;
        }

        public void setDbName(String dbName) {
            if (!TextUtils.isEmpty(dbName)) {
                this.dbName = dbName;
            }
        }

        public int getDbVersion() {
            return dbVersion;
        }

        public void setDbVersion(int dbVersion) {
            this.dbVersion = dbVersion;
        }

        public DbUpgradeListener getDbUpgradeListener() {
            return dbUpgradeListener;
        }

        public void setDbUpgradeListener(DbUpgradeListener dbUpgradeListener) {
            this.dbUpgradeListener = dbUpgradeListener;
        }

        public String getDbDir() {
            return dbDir;
        }

        /**
         * set database dir
         * 
         * @param dbDir
         *            If dbDir is null or empty, use the app default db dir.
         */
        public void setDbDir(String dbDir) {
            this.dbDir = dbDir;
        }
    }

    public interface DbUpgradeListener {
        public void onUpgrade(DbUtils db, int oldVersion, int newVersion);
    }

    private SQLiteDatabase createDatabase(DaoConfig config) {
        SQLiteDatabase result = null;

        String dbDir = config.getDbDir();
        if (!TextUtils.isEmpty(dbDir)) {
            File dir = new File(dbDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File dbFile = new File(dbDir, config.getDbName());
            result = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
        } else {
            result = config.getContext().openOrCreateDatabase(config.getDbName(), 0, null);
        }
        return result;
    }

    // ***************************** private operations with out transaction *****************************
    private void saveOrUpdateWithoutTransaction(Object entity) throws DbException {
        Id id = TableUtils.getId(entity.getClass());
        if (id.isAutoIncrement()) {
            if (id.getColumnValue(entity) != null) {
                execNonQuery(SqlInfoBuilder.buildUpdateSqlInfo(this, entity));
            } else {
                saveBindingIdWithoutTransaction(entity);
            }
        } else {
            execNonQuery(SqlInfoBuilder.buildReplaceSqlInfo(this, entity));
        }
    }

    private boolean saveBindingIdWithoutTransaction(Object entity) throws DbException {
        Class<?> entityType = entity.getClass();
        String tableName = TableUtils.getTableName(entityType);
        Id idColumn = TableUtils.getId(entityType);
        if (idColumn.isAutoIncrement()) {
            List<KeyValue> entityKvList = SqlInfoBuilder.entity2KeyValueList(this, entity);
            if (entityKvList != null && entityKvList.size() > 0) {
                ContentValues cv = new ContentValues();
                DbUtils.fillContentValues(cv, entityKvList);
                long id = database.insert(tableName, null, cv);
                if (id == -1) {
                    return false;
                }
                idColumn.setAutoIncrementId(entity, id);
                return true;
            }
        } else {
            execNonQuery(SqlInfoBuilder.buildInsertSqlInfo(this, entity));
            return true;
        }
        return false;
    }

    // ************************************************ tools ***********************************

    private static void fillContentValues(ContentValues contentValues, List<KeyValue> list) {
        if (list != null && contentValues != null) {
            for (KeyValue kv : list) {
                Object value = kv.getValue();
                if (value != null) {
                    contentValues.put(kv.getKey(), value.toString());
                }
            }
        } else {
            LogUtils.w("List<KeyValue> is empty or ContentValues is empty!");
        }
    }

    public void createTableIfNotExist(Class<?> entityType) throws DbException {
        if (!tableIsExist(entityType)) {
            SqlInfo sqlInfo = SqlInfoBuilder.buildCreateTableSqlInfo(entityType);
            execNonQuery(sqlInfo);
            String execAfterTableCreated = TableUtils.getExecAfterTableCreated(entityType);
            if (!TextUtils.isEmpty(execAfterTableCreated)) {
                execNonQuery(execAfterTableCreated);
            }
        }
    }

    public boolean tableIsExist(Class<?> entityType) throws DbException {
        Table table = Table.get(this, entityType);
        if (table.isCheckedDatabase()) {
            return true;
        }

        Cursor cursor = null;
        try {
            cursor = execQuery("SELECT COUNT(*) AS c FROM sqlite_master WHERE type ='table' AND name ='"
                    + table.getTableName() + "'");
            if (cursor != null && cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    table.setCheckedDatabase(true);
                    return true;
                }
            }
        } finally {
            IOUtils.closeQuietly(cursor);
        }

        return false;
    }

    public void dropDb() throws DbException {
        Cursor cursor = null;
        try {
            cursor = execQuery("SELECT name FROM sqlite_master WHERE type ='table'");
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    try {
                        String tableName = cursor.getString(0);
                        execNonQuery("DROP TABLE " + tableName);
                        Table.remove(this, tableName);
                    } catch (Throwable e) {
                        LogUtils.e(e.getMessage(), e);
                    }
                }
            }
        } finally {
            IOUtils.closeQuietly(cursor);
        }
    }

    public void dropTable(Class<?> entityType) throws DbException {
        if (!tableIsExist(entityType))
            return;
        String tableName = TableUtils.getTableName(entityType);
        execNonQuery("DROP TABLE " + tableName);
        Table.remove(this, entityType);
    }

    // /////////////////////////////////// exec sql /////////////////////////////////////////////////////
    private void debugSql(String sql) {
        if (debug) {
            LogUtils.d(sql);
        }
    }

    private Lock             writeLock   = new ReentrantLock();
    private volatile boolean writeLocked = false;

    private void beginTransaction() {
        if (allowTransaction) {
            database.beginTransaction();
        } else {
            writeLock.lock();
            writeLocked = true;
        }
    }

    private void setTransactionSuccessful() {
        if (allowTransaction) {
            database.setTransactionSuccessful();
        }
    }

    private void endTransaction() {
        if (allowTransaction) {
            database.endTransaction();
        }
        if (writeLocked) {
            writeLock.unlock();
            writeLocked = false;
        }
    }

    public void execNonQuery(SqlInfo sqlInfo) throws DbException {
        debugSql(sqlInfo.getSql());
        try {
            if (sqlInfo.getBindArgs() != null) {
                database.execSQL(sqlInfo.getSql(), sqlInfo.getBindArgsAsArray());
            } else {
                database.execSQL(sqlInfo.getSql());
            }
        } catch (Throwable e) {
            throw new DbException(e);
        }
    }

    public void execNonQuery(String sql) throws DbException {
        debugSql(sql);
        try {
            database.execSQL(sql);
        } catch (Throwable e) {
            throw new DbException(e);
        }
    }

    public Cursor execQuery(SqlInfo sqlInfo) throws DbException {
        debugSql(sqlInfo.getSql());
        try {
            return database.rawQuery(sqlInfo.getSql(), sqlInfo.getBindArgsAsStrArray());
        } catch (Throwable e) {
            throw new DbException(e);
        }
    }

    public Cursor execQuery(String sql) throws DbException {
        debugSql(sql);
        try {
            return database.rawQuery(sql, null);
        } catch (Throwable e) {
            throw new DbException(e);
        }
    }

    // ///////////////////// temp cache ////////////////////////////////////////////////////////////////
    private final FindTempCache findTempCache = new FindTempCache();

    private class FindTempCache {
        private FindTempCache() {
        }

        /**
         * key: sql; value: find result
         */
        private final ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<String, Object>();

        private long                                    seq   = 0;

        public void put(String sql, Object result) {
            if (sql != null && result != null) {
                cache.put(sql, result);
            }
        }

        public Object get(String sql) {
            return cache.get(sql);
        }

        public void setSeq(long seq) {
            if (this.seq != seq) {
                cache.clear();
                this.seq = seq;
            }
        }
    }

}
