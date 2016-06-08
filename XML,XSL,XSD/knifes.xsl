<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
    xmlns:mysp="http://ddd.com/blalbab"
    exclude-result-prefixes="mysp">
    
    <xsl:template match="/">
        <html>
            
            <head>
                <title>Knifes</title>
                <style type="text/css">
                    td.title {
                    background: #efe7d9 ;
                    border-bottom: 1px solid #336699;
                    font: 20 verdana;
                    font-size: 170%;
                    font-weight: 600;
                    padding: 0 0 0 15px;
                    }
                    td.first
                    
                    {
                    font-size: 150%;
                    padding-left: 20px;
                    
                    }
                    td.second
                    
                    {
                    font-size: 100%;
                    padding-left: 30px;
                    
                    }
                    td.therd
                    
                    {
                    padding-left: 40px;
                    font-size: 70%;
    
                    }
                
                </style>
            </head>
            
            <body>
                <table> 
                    
                    <xsl:for-each select="knifes/knife">
                        <tr></tr>
                        <tr>               
                            <td colspan="2" class="title"><xsl:value-of select="type" /></td>
                        </tr>
                        <tr>
                            <td  class="first">Hangry:</td>
                            <td  class="first"><xsl:value-of select="hangry" /></td>
                        </tr>
                        <tr>
                            <td class="first">Origin:</td>
                            <td class="first"><xsl:value-of select="origin" /></td>
                        </tr>
                        <tr>
                            <td class="first">Visual</td>
                        </tr>
                        <tr>
                            <td class="second">Handle:</td>
                            <td class="second"><xsl:value-of select="visual/handle" /></td>
                        </tr>
                        <tr>
                            <td class="second">Value:</td>
                            <td class="second"><xsl:value-of select="visual/value" /></td>
                        </tr>
                        <tr><td class="second">Blade</td></tr>
                        <tr>
                            <td class="therd">Lenght:</td>
                            <td class="therd"><xsl:value-of select="visual/blade/mysp:lenght"/></td>
                        </tr>
                        <tr>
                            <td class="therd">Width:</td>
                            <td class="therd"><xsl:value-of select="visual/blade/mysp:width"/></td>
                        </tr>
                        <tr>
                            <td class="therd">Material:</td>
                            <td class="therd"><xsl:value-of select="visual/blade/mysp:material"/></td>
                        </tr>
                        <tr>
                            <td class="therd">Blood_groove:</td>
                            <td class="therd"><xsl:value-of select="visual/blade/mysp:blood_groove"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>