drop table if exists `chapter`;
create table `chapter` (
    `id` char(8) not null comment 'ID',
    `course_id` char(8) comment '课程ID',
    `name` varchar(50) comment '名称',
    primary key (`id`)
)engine = innodb default charset=utf8mb4 comment='大章';

insert into `chapter` (id, course_id, name) values ('00000000', '00000000', '测试大章01');
insert into `chapter` (id, course_id, name) values ('00000001', '00000000', '测试大章02');
insert into `chapter` (id, course_id, name) values ('00000002', '00000000', '测试大章03');
insert into `chapter` (id, course_id, name) values ('00000003', '00000000', '测试大章04');
insert into `chapter` (id, course_id, name) values ('00000004', '00000000', '测试大章05');
insert into `chapter` (id, course_id, name) values ('00000005', '00000000', '测试大章06');
insert into `chapter` (id, course_id, name) values ('00000006', '00000000', '测试大章07');
insert into `chapter` (id, course_id, name) values ('00000007', '00000000', '测试大章08');
insert into `chapter` (id, course_id, name) values ('00000008', '00000000', '测试大章09');
insert into `chapter` (id, course_id, name) values ('00000009', '00000000', '测试大章10');
insert into `chapter` (id, course_id, name) values ('00000010', '00000000', '测试大章11');
insert into `chapter` (id, course_id, name) values ('00000011', '00000000', '测试大章12');
insert into `chapter` (id, course_id, name) values ('00000012', '00000000', '测试大章13');
insert into `chapter` (id, course_id, name) values ('00000013', '00000000', '测试大章14');

-- 小节
drop table if exists `section`;
create table `section` (
    `id` char(8) not null default '' comment 'ID',
    `title` varchar(50) not null comment '标题',
    `course_id` char(8) comment '课程|course.id',
    `chapter_id` char(8) comment '大章|chapter.id',
    `video` varchar(200) comment '视频',
    `time` int comment '时长|单位秒',
    `charge` char(1) comment '收费|C 收费; F 免费',
    `sort` int comment '顺序',
    `created_at` datetime(3) comment '创建时间',
    `updated_at` datetime(3) comment '修改时间',
    primary key (`id`)
)engine=InnoDB default charset=utf8mb4 comment='小节';

insert into `section` (id, title, course_id, chapter_id, video, time, charge, sort, created_at, updated_at)
values ('00000001', '测试小节01', '00000001', '00000000', '', 500, 'F', 1, now(), now());

-- 课程
drop table if exists `course_info`;
create table `course_info` (
    id char(8) not null default '' comment 'id',
    name varchar(50) not null comment '名称',
    summary varchar(2000) comment '概述',
    time int default 0 comment '时长|单位秒',
    price decimal(8, 2) default 0.00 comment '价格(元)',
    image varchar(100) comment '封面',
    level char(1) comment '级别|枚举[CourseLevelEnum]: ONE("1", "初级"), TWO("2", "中级"), THREE("3", "高级")',
    charge char(1) comment '收费|枚举[CourseChargeEnum]: CHARGE("C", "收费"), FREE("F", "免费")',
    status char(1) comment '状态|枚举[CourseStatusEnum]: PUBLISH("P", "发布"), DRAFT("D", "草稿")',
    enroll integer default 0 comment '报名数',
    sort int comment '顺序',
    created_at datetime(3) comment '创建时间',
    updated_at datetime(3) comment '修改时间',
    primary key (id)
)engine=InnoDB default charset=utf8mb4 comment='课程表';

insert into course_info (id, name, summary, time, price, image, level, charge, status, enroll, sort, created_at, updated_at)
values ('00000001', '测试课程01', '这是一门测试课程', 7200, 19.9, '', 0, 'C', 'D', 100, 0, now(), now());

update course_info c set `time` = (select sum(`time`) from `section` where course_id = '00000001')
    where c.id = '00000001';

-- 分类
drop table if exists `category`;
create table `category` (
    `id` char(8) not null default '' comment 'id',
    `parent` char(8) not null default '' comment '父id',
    `name` varchar(50) not null comment '名称',
    `sort` int comment '顺序',
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='分类';

insert into `category` (id, parent, name, sort) values ('00000100', '00000000', '前端技术', 100);
insert into `category` (id, parent, name, sort) values ('00000101', '00000100', 'html/css', 101);
insert into `category` (id, parent, name, sort) values ('00000102', '00000100', 'javascript', 102);
insert into `category` (id, parent, name, sort) values ('00000103', '00000100', 'vue.js', 103);
insert into `category` (id, parent, name, sort) values ('00000104', '00000100', 'react.js', 104);
insert into `category` (id, parent, name, sort) values ('00000105', '00000100', 'angular', 105);
insert into `category` (id, parent, name, sort) values ('00000106', '00000100', 'node.js', 106);
insert into `category` (id, parent, name, sort) values ('00000107', '00000100', 'jquery', 107);
insert into `category` (id, parent, name, sort) values ('00000108', '00000100', '小程序', 108);
insert into `category` (id, parent, name, sort) values ('00000200', '00000000', '后端技术', 200);
insert into `category` (id, parent, name, sort) values ('00000201', '00000200', 'java', 201);
insert into `category` (id, parent, name, sort) values ('00000202', '00000200', 'springboot', 202);
insert into `category` (id, parent, name, sort) values ('00000203', '00000200', 'spring cloud', 203);
insert into `category` (id, parent, name, sort) values ('00000204', '00000200', 'ssm', 204);
insert into `category` (id, parent, name, sort) values ('00000205', '00000200', 'python', 205);
insert into `category` (id, parent, name, sort) values ('00000206', '00000200', '爬虫', 206);
insert into `category` (id, parent, name, sort) values ('00000300', '00000000', '移动开发', 300);
insert into `category` (id, parent, name, sort) values ('00000301', '00000300', 'android', 301);
insert into `category` (id, parent, name, sort) values ('00000302', '00000300', 'ios', 302);
insert into `category` (id, parent, name, sort) values ('00000303', '00000300', 'react native', 303);
insert into `category` (id, parent, name, sort) values ('00000304', '00000300', 'ionic', 304);