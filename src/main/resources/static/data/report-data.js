$(function () {

    let memberTrendData = [{
        period: '2017',
        fullMembers: 500,
        legacyMembers: 102
    }, {
        period: '2018',
        fullMembers: 651,
        legacyMembers: 99
    }, {
        period: '2019',
        fullMembers: 780,
        legacyMembers: 97
    }, {
        period: '2020',
        fullMembers: 723,
        legacyMembers: 93
    }, {
        period: '2021',
        fullMembers: 710,
        legacyMembers: 91
    }, {
        period: '2022',
        fullMembers: 680,
        legacyMembers: 90
    }, {
        period: '2023',
        fullMembers: 672,
        legacyMembers: 88
    }, {
        period: '2024',
        fullMembers: 650,
        legacyMembers: 84
    }, {
        period: '2025',
        fullMembers: 599,
        legacyMembers: 82
    }];
    
    Morris.Area({
        element: 'member-trend-area-chart',
        data: memberTrendData,
        xkey: 'period',
        ykeys: ['fullMembers', 'legacyMembers'],
        labels: ['Full Members', 'Legacy Members'],
        pointSize: 2,
        hideHover: 'auto',
        resize: true
    });

    let memberData = [{
        label: "Legacy Members",
        value: 80
    }, {
        label: "Full Members",
        value: 654
    }];

    let payerData = [{
        label: "Monthly Fanbase",
        value: 10
    }, {
        label: "Monthly Standing Order",
        value: 500
    }, {
        label: "Quarterly Standing Order",
        value: 6
    }, {
        label: "Annual Standing Order",
        value: 50
    }];

    Morris.Donut({
        element: 'member-donut-chart',
        data: memberData,
        resize: true
    });

    Morris.Donut({
        colors: ['#5cb85c', '#7cc67c', '#9dd49d', '#bde2bd'],
        element: 'payer-donut-chart',
        data: payerData,
        resize: true
    });

    let prizesIssuedData = [
        { year: '2017', value: 20000 },
        { year: '2018', value: 40000 },
        { year: '2019', value: 60000 },
        { year: '2020', value: 65000 },
        { year: '2021', value: 85000 },
        { year: '2022', value: 100300 },
        { year: '2023', value: 123100 },
        { year: '2024', value: 146500 }
    ];
    Morris.Line({
        lineColors: ['#f0ad4e'],
        // ID of the element in which to draw the chart.
        element: 'prizes-line-chart',
        // Chart data records -- each entry in this array corresponds to a point on
        // the chart.
        data: prizesIssuedData,
        // The name of the data record attribute that contains x-values.
        xkey: 'year',
        // A list of names of data record attributes that contain y-values.
        ykeys: ['value'],
        // Labels for the ykeys -- will be displayed when you hover over the
        // chart.
        labels: ['Running total (£)']
    });

});
