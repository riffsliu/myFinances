select 
cat.descricaocategoria,
sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='01-2018' THEN t.valortransacao ELSE 0 END) AS mes01,
sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='02-2018' THEN t.valortransacao ELSE 0 END) AS mes02,
sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='03-2018' THEN t.valortransacao ELSE 0 END) AS mes03,
sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='04-2018' THEN t.valortransacao ELSE 0 END) AS mes04,
sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='05-2018' THEN t.valortransacao ELSE 0 END) AS mes05,
sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='06-2018' THEN t.valortransacao ELSE 0 END) AS mes06,
sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='07-2018' THEN t.valortransacao ELSE 0 END) AS mes07,
sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='08-2018' THEN t.valortransacao ELSE 0 END) AS mes08,
sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='09-2018' THEN t.valortransacao ELSE 0 END) AS mes09,
sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='10-2018' THEN t.valortransacao ELSE 0 END) AS mes10,
sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='11-2018' THEN t.valortransacao ELSE 0 END) AS mes11,
sum(CASE WHEN to_char(t.datatransacao,'MM-YYYY') ='12-2018' THEN t.valortransacao ELSE 0 END) AS mes12
from transacao t inner join categoria cat on t.idcategoria=cat.idcategoria
group by 
cat.idcategoria
order by cat.idtipocategoria desc;

select * from transacao where idcategoria =1 order by descricaotransacao;

select * from transacao where descricaotransacao like '%SEM P%' and idcategoria=1;

select * from categoria;

select * from transacao where idcategoria =18


update transacao set idcategoria =19 where idcategoria =1 and descricaotransacao like'%SEM P%';





