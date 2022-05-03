$(document).ready(function() {
	$('#dateOfBirth').attr('max', new Date().toISOString().split('T')[0]);

	$('#saveBtn').on('click', function() {
		if (isValidateBirthDate()) {
			$('#member-form').submit();
		} else {
			$('#dobErr').text('Invalid Date Format');
		}
	});

	function isValidateBirthDate() {
		// var dateStr = $('#dateOfBirth').val();
		// var timestamp = Date.parse(dateStr);
		// return !isNaN(timestamp)
		$('#dateOfBirth').attr('max', new Date().toISOString().split('T')[0]);

	}

	$('#gotoListBtn').on('click', function() {
		window.location = "/member/list";
	});
});