var orderId = decodeURIComponent(window.location.search.substring(1));
$.ajax({
    type: 'post',
    url: urlWay.clinicHost + 'clinicOrderStatus.action',
    cache: false,
    async: false,
    data: {
        action: "updateClinicOrderStatus",
        "orderInfoClinic.id": orderId,
        "orderInfoClinic.orderStatus": "已会面"
    },
    success: function (result) {

    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        alert(XMLHttpRequest.status);
        alert(XMLHttpRequest.readyState);
        alert(textStatus);
        alert(errorThrown);
    }
});
