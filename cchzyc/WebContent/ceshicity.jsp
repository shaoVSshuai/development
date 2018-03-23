<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
        <title>全国城市三级联动</title>
        <style type="text/css">
            .ellipsis{
                width: 85px;
                max-width: 85px;
                white-space:nowrap;
                text-overflow:ellipsis;
                overflow:hidden;
            }
        </style>
        <script type="text/javascript" src="js/loadCity.js" charset=”utf-8″></script>
    </head>

    <body>
        <div class="top">
            <h1>全国城市三级联动</h1>
        </div>
        <div class="info">
            <div>
                <select id="s_province" class="ellipsis" name="s_province"></select>
                <select id="s_city" class="ellipsis" name="s_city"></select>
                <select id="s_county" class="ellipsis" name="s_county"></select>                
            </div>
            <div id="show"></div>
        </div>
      

        <script type="text/javascript">
            _init_area();
            var showArea = function() {
                document.getElementById('show').innerHTML = "<h3>省" + document.getElementById('s_province').value + " - 市" +
                    document.getElementById('s_city').value + " - 县/区" +
                    document.getElementById('s_county').value + "</h3>"
            };
            document.getElementById('s_county').setAttribute('onchange', 'showArea()');
        </script>
    </body>
</html>