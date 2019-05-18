package club.xyes.zkh.retail.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create by 郭文梁 2019/5/17 0017 17:30
 * Store
 * 商家实体类
 *
 * @author 郭文梁
 * @data 2019/5/17 0017
 */
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_store")
@Data
public class Store extends AbstractEntity {
    /**
     * 商铺名称
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * 管理人姓名
     */
    @Column(name = "keeper_name", nullable = false)
    private String keeperName;
    /**
     * 电话
     */
    @Column(name = "phone", nullable = false)
    private String phone;
    /**
     * 经度
     */
    @Column(name = "longitude", length = 100)
    private String longitude;
    /**
     * 纬度
     */
    @Column(name = "latitude", length = 100)
    private String latitude;
    /**
     * 省
     */
    @Column(name = "province", length = 100)
    private String province;
    /**
     * 市
     */
    @Column(name = "city", length = 100)
    private String city;
    /**
     * 区
     */
    @Column(name = "region", length = 100)
    private String region;
    /**
     * 详细地址
     */
    @Column(name = "address")
    private String address;
    /**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;
    /**
     * 密码
     */
    @Column(name = "password")
    private String password;
}
