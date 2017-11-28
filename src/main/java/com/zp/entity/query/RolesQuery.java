/**
 * Copyright © 2016郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: RolesQuery
 * @Prject: pss-authority
 * @Package: com.pss.entity.query
 * @author: qrc
 * @date: 2017-02-17 16:57
 * @version: V1.0
 */

package com.zp.entity.query;

import java.util.HashSet;
import java.util.Set;

import com.zp.entity.Resources;
import com.zp.entity.Roles;

/**
 * @Title: RolesQuery
 * @Description: <继承Roles实体类，新增业务性>
 * @author: qrc
 * @date: 2017-02-17 16:57
 * @version: V1.0
 */
public class RolesQuery extends Roles {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -800973613118981896L;
	/**
     * 资源集合
     */
    private Set<Resources> resources = new HashSet<>();


    public Set<Resources> getResources() {
        return resources;
    }

    public void setResources(Set<Resources> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "RolesQuery{" +
                "resources=" + resources +
                '}';
    }
}
