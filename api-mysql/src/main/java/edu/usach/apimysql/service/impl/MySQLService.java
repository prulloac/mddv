package edu.usach.apimysql.service.impl;

import edu.usach.apicommons.service.ExtractorService;
import edu.usach.apimysql.extractor.MySQLExtractor;
import edu.usach.apimysql.service.IMySQLService;
import org.springframework.stereotype.Service;

@Service
public class MySQLService extends ExtractorService<MySQLExtractor> implements IMySQLService {

    @Override
    protected MySQLExtractor buildExtractor() {
        return new MySQLExtractor();
    }

}
