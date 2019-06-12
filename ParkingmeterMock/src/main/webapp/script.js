$(function () {
    $('#getTicketButton').click(function () {
        var parkingMeterId = $('#parkingMeterId').val();
        var minutes = $('#minutes').val();
        var endTime = new Date().getTime() + minutes * 60 * 1000;
        var url = "http://localhost:8080/MainApplication/rest/ticket";
        var data = JSON.stringify({
            'duration': minutes,
            'endTime': endTime,
            'parkingMeterId': parkingMeterId
        });

        $.ajax({
            type: 'POST',
            url: url,
            contentType: 'application/json',
            dataType: 'json',
            data: data
        });
    });
});