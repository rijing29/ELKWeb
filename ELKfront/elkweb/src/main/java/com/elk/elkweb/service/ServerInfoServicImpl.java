package com.elk.elkweb.service;

import com.elk.elkweb.entity.ServerPublic;
import com.elk.elkweb.mapper.ServerInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ServerInfoServicImpl implements ServerInfoService {
    @Autowired
    private ServerInfoMapper serverInfoMapper;
    public List<ServerPublic> showServerPublic() {
        List<ServerPublic> serverPublics = serverInfoMapper.showServerPublic();
        return serverPublics;
    }

    @Override
    public List<Map<String, Object>> getStorage() {
        List<Map<String, Object>> storage = serverInfoMapper.getStorage();
        return storage;
    }

    @Override
    public List<Map<String, Object>> getStorageInfo(String DIRNAME) {
        List<Map<String, Object>> storageInfo = serverInfoMapper.getStorageInfo(DIRNAME);
        return storageInfo;
    }
}
