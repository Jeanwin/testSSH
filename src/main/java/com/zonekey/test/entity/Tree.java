package com.zonekey.test.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 树形菜单实体类
 * 
 * @author admin
 * 
 */
@Entity
@Table(name = "tree")
public class Tree implements Serializable {
	private static final long serialVersionUID = -8784903489249384188L;

	private Integer id;
	private String name;
	private String title;
	private String description;
	private Integer parentId;

	private List<Tree> nodes;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * (1)targetEntity属性表示默认关联的实体类型，默认为当前标注的实体类；
	 * 因为一对多的实体集合时保存在集合类中，因此必须指明集合类中保存的具体类型：<br>
	 * 1）指定集合泛型的具体类型；如：public Collection<AddressEO> getAddress() {...
	 * 2）指定targetEntity属性类型；如：@OneToMany(
	 * targetEntity=AddressEO.class,casade={CascadeTypeType.ALL})<br>
	 * 
	 * (2)cascade属性表示与此实体一对一关联的实体的联级样式类型。联级样式上当对实体进行操作时的策略。
	 * 说明：在定义关系时经常会涉及是否定义Cascade(级联处理)属性，担心造成负面影响. ·不定义,则对关系表不会产生任何影响
	 * 1)CascadeType.PERSIST （级联新建）<br>
	 * 2)CascadeType.REMOVE （级联删除）<br>
	 * 3)CascadeType.REFRESH （级联刷新）<br>
	 * 4)CascadeType.MERGE （级联更新）中选择一个或多个。<br>
	 * 5)还有一个选择是使用CascadeType.ALL ，表示选择全部四项 <br>
	 * 
	 * (3)fetch属性是该实体的加载方式，有两种：<br>
	 * 1)LAZY(使用select关联查询)，速度慢<br>
	 * 2)EAGER(使用left outer join查询),速度稍快一些。<br>
	 * 默认为惰性加载，一般也建议使用惰性加载。<br>
	 * 在需要获取所有节点时(不重复的list结构)应使用延迟加载，可以避免1+n次查询
	 * 
	 * (4)mappedBy属性用于双向关联实体时使用。
	 * 
	 * @return
	 */
	@OneToMany(targetEntity = Tree.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	public List<Tree> getNodes() {
		return nodes;
	}

	public void setNodes(List<Tree> nodes) {
		this.nodes = nodes;
	}

	@Override
	public String toString() {
		return "Tree [id=" + id + ", name=" + name + ", title=" + title + ", description=" + description + ", parentId=" + parentId + ", nodes=" + nodes + "]";
	}
}
