/**
 * Copyright 2011-2018 Asakusa Framework Team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jobflow;

import org.junit.Test;

import com.asakusafw.testdriver.JobFlowTester;
import com.example.modelgen.dmdl.model.CategorySummary;
import com.example.modelgen.dmdl.model.ErrorRecord;
import com.example.modelgen.dmdl.model.ItemInfo;
import com.example.modelgen.dmdl.model.SalesDetail;
import com.example.util.CountVerifier;


/**
 * {@link CategorySummaryJob}のテスト。
 */
public class CategorySummaryJobTest {

    /**
     * 最小限のテスト。
     */
    @Test
    public void simple() {
        run("simple.xls", 0);
    }

    /**
     * カテゴリ別の集計を行うテスト。
     */
    @Test
    public void summarize() {
        run("summarize.xls", 0);
    }

    /**
     * マスタ有効期限のテスト。
     */
    @Test
    public void available_date() {
        run("available_range.xls", 1);
    }
    private void run(String dataSet, long errors) {
        JobFlowTester tester = new JobFlowTester(getClass());
        tester.setBatchArg("date", "testing");

        tester.input("itemInfo", ItemInfo.class)
            .prepare("masters.xls#item_info");

        tester.input("salesDetail", SalesDetail.class)
            .prepare(dataSet + "#sales_detail");
        tester.output("categorySummary", CategorySummary.class)
            .verify(dataSet + "#result", dataSet + "#result_rule");
        tester.output("errorRecord", ErrorRecord.class)
            .verify(CountVerifier.factory(errors));

        tester.runTest(CategorySummaryJob.class);
    }
}
