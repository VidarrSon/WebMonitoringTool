INSERT INTO url_table (url_address, status, url_name, monitoring_period, response_time,
                       response_code, response_substring, response_range_min, response_range_max) VALUES (
                       'https://twitter.com/', 'Unknown', 'Twitter', 5000, 500, 200, 'window', 400000, 1);
INSERT INTO url_table (url_address, status, url_name, monitoring_period, response_time,
                       response_code, response_substring, response_range_min, response_range_max) VALUES (
                       'https://facebook.com/', 'Unknown', 'Facebook', 10000, 200, 200, 'substringthatshouldbefind', 500000, 5);
INSERT INTO url_table (url_address, status, url_name, monitoring_period, response_time,
                       response_code, response_substring, response_range_min, response_range_max) VALUES (
                       'https://stackoverflow.com/', 'Unknown', 'Stack Overflow', 60000, '', 1000, 200, 300000, 30);
INSERT INTO url_table (url_address, status, url_name, monitoring_period, response_time,
                       response_code, response_substring, response_range_min, response_range_max) VALUES (
                       'https://www.youtube.com/', 'Unknown', 'Youtube', 30000, 1000, 200, 'andstillstring', 600000, 1);