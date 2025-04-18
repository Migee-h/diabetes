
<template>
  <div class="app-container">
    <!-- 增加一个外层容器 -->
    <el-row :gutter="20" style="width: 100%;">
      <el-col :span="8">
        <div class="stats-card">
          <div class="stats-title">15天平均血糖值</div>
          <div class="stats-value">{{ sugarStatsData.averageSugarValue || '-' }}</div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="stats-card">
          <div class="stats-title">每日达标率</div>
          <div class="stats-value">{{ sugarStatsData.dailyQualifiedRate || '-' }}%</div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="stats-card warning" v-if="sugarStatsData.hasWarning">
          <div class="stats-title">警告信息</div>
          <div class="stats-value">{{ sugarStatsData.warningMessage }}</div>
        </div>
      </el-col>
    </el-row>

    <el-collapse class="mb20">
      <el-collapse-item title="血糖趋势图" name="1">
        <div class="chart-container">
          <div ref="sugarChart" style="width: 100%; height: 400px;"></div>
        </div>
      </el-collapse-item>
    </el-collapse>

    <el-collapse>
      <el-collapse-item title="达标率统计" name="2">
        <div class="chart-container">
          <div ref="qualifiedChart" style="width: 100%; height: 400px;"></div>
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
</script>

<style scoped>
.sugar-stats {
  padding: 20px;
}

.stats-card {
  background: #fff;
  border-radius: 4px;
  padding: 20px;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);
  margin-bottom: 20px; /* 增加底部间距 */
}

.stats-title {
  font-size: 14px;
  color: #606266;
  margin-bottom: 10px;
}

.stats-value {
  font-size: 24px;
  color: #303133;
}

.warning {
  background: #fef0f0;
}

.warning .stats-value {
  color: #f56c6c;
}

.chart-container {
  padding: 20px;
  background: #fff;
  border-radius: 4px;
  width: 100%;
  box-sizing: border-box;
  height: 400px; /* 根据需要调整 */
}
</style>