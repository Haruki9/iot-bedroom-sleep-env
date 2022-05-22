var sleepData;
var sleepInfoDom=document.getElementById("sleep-route");
var sleepCharts=echarts.init(sleepInfoDom);
getSleepInfo();

var sleepInfoOption={
    tooltip: {
        trigger: 'item'
    },
    legend: {
        top: '5%',
        orient: "vertical",
        left: 'left',
        textStyle:{
            color: "#9aa8d4",
        }
    },
    series: [
        {
            name: 'Sleep Info',
            type: 'pie',
            label: {
                show: true,
                rotate: false,
                color: "#97f64b",
            },
            emphasis: {
                label: {
                    show: true,
                    fontSize: '25',
                    fontWeight: 'bold',
                    color: "#97f64b",
                }
            },
            labelLine: {
                show: true
            },
            data: sleepData
        }
    ]
}

function getSleepInfo(){
    $.ajax({
        url: "bedroom/sleep-info",
        type: "GET",
        data:{
            date: today,
        },
        success:function (sleepInfo){
            var rem=sleepInfo.remSleep;
            var deep=sleepInfo.deepSleep;
            var normal=100-rem-deep;
            document.getElementById("score").innerText=sleepInfo.score;
            document.getElementById("heart-rate").innerText=sleepInfo.heartRate;
            document.getElementById("start-at").innerText=sleepInfo.startAt;
            document.getElementById("end-at").innerText=sleepInfo.endAt;
            sleepData=[
                {value: rem,name: "轻度睡眠"},
                {value: deep,name: "深度睡眠"},
                {value: normal,name: "普通睡眠"},
            ]
            sleepInfoOption.series[0].data=sleepData;
            sleepCharts.setOption(sleepInfoOption);
        },
        error: function (){
            console.log("Sleep Info get error.")
        }
    })
}


var weekSleepDom=document.getElementById("week-sleep-info");
var weekSleepCharts=echarts.init(weekSleepDom);
var sevenDays=[]
var deepData=[]
var remData=[]
var normalData=[]

var weekSleepOption={
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        textStyle:{
            color: "#9aa8d4",
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: [
        {
            type: 'category',
            data: sevenDays,
            axisLine: {
                lineStyle: {
                    color: '#00a2e2',
                    width: 1, // 这里是为了突出显示加上的
                }
            },
        }
    ],
    yAxis: [
        {
            type: 'value'
        }
    ],
    series: [
        {
            name: '轻度睡眠',
            type: 'bar',
            stack: 'sleep-circle',
            emphasis: {
                focus: 'series'
            },
            data: remData
        },
        {
            name: '中度睡眠',
            type: 'bar',
            stack: 'sleep-circle',
            emphasis: {
                focus: 'series'
            },
            data: normalData
        },
        {
            name: '深度睡眠',
            type: 'bar',
            stack: 'sleep-circle',
            emphasis: {
                focus: 'series'
            },
            data: deepData
        },
    ]
}

function getSevenDaysSleepInfo(){
    $.ajax({
        url: "bedroom/sevendays-sleep-info",
        type: "GET",
        data:{
            lastDate: today,
        },
        success:function (sleepInfo){
            sleepInfo.forEach((oneDay)=>{
                sevenDays.push(oneDay.date);
                remData.push(oneDay.remSleep);
                deepData.push(oneDay.deepSleep);
                normalData.push(100-oneDay.remSleep-oneDay.deepSleep);
            })
            weekSleepOption.xAxis[0].data=sevenDays;
            weekSleepOption.series[0].data=remData;
            weekSleepOption.series[1].data=normalData;
            weekSleepOption.series[2].data=deepData;
            weekSleepCharts.setOption(weekSleepOption);
        },
        error: function (){
            console.log("sevenDays Sleep Info get error.")
        }
    })
}

getSevenDaysSleepInfo();
