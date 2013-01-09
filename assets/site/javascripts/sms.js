loadcontent = function() {

	var search = '/sms';
	var path = window.location.href.substring(window.location.href.indexOf(search) + search.length);
	if (path.length == '')
		path = '/';
	console.log(path);
	$.get('/json/sms'  + path, function(data) {
		if (!data) {
			return;
		}

		for (var sms in data.smss) {
			sms = data.smss[sms];
			var format =
				".row.sms\n" +
				"  .span6\n" +
				"    span.message=sms.message\n" +
				"  .span1.number=sms.number\n" +
				"  .span.info=sms.info";
			jade.render(format, {
				sms: sms
			}, function(err, data) {
				if (err) {
					console.log(err);
					return;
				}
				
				$('#sms-rows').append(data);
			})
		}
	})
}

loadcontent();


