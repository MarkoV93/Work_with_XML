<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    
    <xsl:template match="/">
        <html>
            
            <head>
                <title>CD Catalog</title>
                <style type="text/css">
                    td.greenRow
                    {
                    
                    border-bottom: 1px solid #437841;
                    
                    }
                </style>
            </head>
            
            <body>
                <table> 
                    
                    <xsl:for-each select="catalog/cd">
                        <tr>     
                            
                            <td colspan="2" class="greenRow"><xsl:value-of select="type" /></td>
                        </tr>
                        <tr>
                            <td>Artist:</td>
                            <td><xsl:value-of select="hangry" /></td>
                        </tr>
                        <tr>
                            <td>Company:</td>
                            <td><xsl:value-of select="origin" /></td>
                        </tr>
                        <tr>
                            <td>Country:</td>
                            <td><xsl:value-of select="handle" /></td>
                        </tr>
                        <tr>
                            <td>Year:</td>
                            <td><xsl:value-of select="value" /></td>
                        </tr>
                        <tr>
                            <td>Price:</td>
                            <td> <xsl:value-of select="lenght" /></td>  
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>