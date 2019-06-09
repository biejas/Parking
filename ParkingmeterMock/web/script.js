$(function () {
    $('#getTicketButton').click(function () {
        var parkingMeterId = $('#parkingMeterId').val();
        var minutes = $('#minutes').val();
        var expirationTime = new Date().getTime() + minutes * 60 * 1000;
        var url = "http://localhost:8080/parking-area-service/rest/ticket";
        var data = JSON.stringify({
            'duration': minutes,
            'expirationTime': expirationTime,
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