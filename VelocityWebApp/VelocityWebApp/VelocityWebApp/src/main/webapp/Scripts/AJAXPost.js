/**
 * JS to submit the calc data
 */
   $(document).ready(function() {
            $('#calculateButton').click(function() {
                const distance = $('#distance').val();
                const time = $('#time').val();
                
                if (distance && time) {
                    const data = { distance: parseFloat(distance), time: parseFloat(time) };
                    
                    $.ajax({
                        url: '/VelocityWebApp/CalcAPI',
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify(data),
                        success: function(response) {
                            $('#velocity').text(`Velocity: ${response.velocity} m/s`);
                        },
                        error: function(error) {
                            console.error('Error:', error);
                            alert('Failed to calculate velocity.');
                        }
                    });
                } else {
                    alert('Please fill in both fields');
                }
            });
        });