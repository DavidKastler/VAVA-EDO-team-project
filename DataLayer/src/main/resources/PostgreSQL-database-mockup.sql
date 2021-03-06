truncate roles cascade;
--Roles
insert into roles (r_id, role_name, manager_rights, todo_access_rights, team_leader_rights, admin_rights) values (1, 'admin', true, true, true, true);
insert into roles (r_id, role_name, manager_rights, todo_access_rights, team_leader_rights, admin_rights) values (2, 'account_manager', true, true, true, false);
insert into roles (r_id, role_name, manager_rights, todo_access_rights, team_leader_rights, admin_rights) values (3, 'team_leader', false, true, true, false);
insert into roles (r_id, role_name, manager_rights, todo_access_rights, team_leader_rights, admin_rights) values (4, 'pleb', false, true, false, false);
insert into roles (r_id, role_name, manager_rights, todo_access_rights, team_leader_rights, admin_rights) values (5, 'guest', false, false, false, false);

--Users
insert into users (u_id, username, password, role_id) values (1, 'Laney', 'ZWNuDaQU', 5);
insert into users (u_id, username, password, role_id) values (2, 'Clare', 'QBB5sLeIE', 4);
insert into users (u_id, username, password, role_id) values (3, 'Pauletta', 'oFE0IChy8P', 4);
insert into users (u_id, username, password, role_id) values (4, 'Ruggiero', 'RKnogE', 4);
insert into users (u_id, username, password, role_id) values (5, 'Kalvin', 'VfP1MsV', 1);
insert into users (u_id, username, password, role_id) values (6, 'Erroll', 'z6UrOP', 4);
insert into users (u_id, username, password, role_id) values (7, 'Lorry', 'HLS26VLy', 2);
insert into users (u_id, username, password, role_id) values (8, 'Donnell', 'NulgVFiMbcu', 2);
insert into users (u_id, username, password, role_id) values (9, 'Russ', 'desltGlLzaN', 3);
insert into users (u_id, username, password, role_id) values (10, 'Kikelia', 'bLtgTqV5pW8', 3);

--Groups
insert into groups (gr_id, group_name, group_creator_id) values (1, 'Lotlux', 5);
insert into groups (gr_id, group_name, group_creator_id) values (2, 'Opela', 9);
insert into groups (gr_id, group_name, group_creator_id) values (3, 'Mat Lam Tam', 9);
insert into groups (gr_id, group_name, group_creator_id) values (4, 'Bigtax', 9);
insert into groups (gr_id, group_name, group_creator_id) values (5, 'Flowdesk', 8);
insert into groups (gr_id, group_name, group_creator_id) values (6, 'Konklux', 10);
insert into groups (gr_id, group_name, group_creator_id) values (7, 'Span', 9);

--Group members
insert into group_members (gm_id, group_id, member_id) values (1, 2, 9);
insert into group_members (gm_id, group_id, member_id) values (2, 4, 9);
insert into group_members (gm_id, group_id, member_id) values (3, 4, 2);
insert into group_members (gm_id, group_id, member_id) values (4, 7, 4);
insert into group_members (gm_id, group_id, member_id) values (5, 4, 3);
insert into group_members (gm_id, group_id, member_id) values (6, 6, 3);
insert into group_members (gm_id, group_id, member_id) values (7, 5, 3);
insert into group_members (gm_id, group_id, member_id) values (8, 6, 4);
insert into group_members (gm_id, group_id, member_id) values (9, 6, 2);
insert into group_members (gm_id, group_id, member_id) values (10, 2, 10);
insert into group_members (gm_id, group_id, member_id) values (11, 2, 5);
insert into group_members (gm_id, group_id, member_id) values (12, 5, 2);
insert into group_members (gm_id, group_id, member_id) values (13, 3, 3);
insert into group_members (gm_id, group_id, member_id) values (14, 3, 4);
insert into group_members (gm_id, group_id, member_id) values (15, 7, 9);
insert into group_members (gm_id, group_id, member_id) values (16, 1, 5);
insert into group_members (gm_id, group_id, member_id) values (17, 3, 9);
insert into group_members (gm_id, group_id, member_id) values (18, 5, 8);
insert into group_members (gm_id, group_id, member_id) values (19, 6, 10);

--Relationships
insert into relationships (rel_id, first_user_id, second_user_id, status, since) values (1, 3, 6,  'PENDING', '1637329970');
insert into relationships (rel_id, first_user_id, second_user_id, status, since) values (2, 7, 10, 'BLOCKED', '1625218370');
insert into relationships (rel_id, first_user_id, second_user_id, status, since) values (3, 3, 9,  'BLOCKED', '1623168110');
insert into relationships (rel_id, first_user_id, second_user_id, status, since) values (4, 5, 4,  'PENDING', '1621133330');
insert into relationships (rel_id, first_user_id, second_user_id, status, since) values (5, 2, 7,  'PENDING', '1625194130');
insert into relationships (rel_id, first_user_id, second_user_id, status, since) values (6, 8, 10, 'ACCEPTED', '1616416542');
insert into relationships (rel_id, first_user_id, second_user_id, status, since) values (7, 10, 8, 'ACCEPTED', '1616416542');
insert into relationships (rel_id, first_user_id, second_user_id, status, since) values (8, 4, 3,  'ACCEPTED', '1642416485');
insert into relationships (rel_id, first_user_id, second_user_id, status, since) values (9, 3, 4,  'ACCEPTED', '1642416485');
insert into relationships (rel_id, first_user_id, second_user_id, status, since) values (10, 5, 2,  'ACCEPTED', '1621784562');
insert into relationships (rel_id, first_user_id, second_user_id, status, since) values (11, 2, 5,  'ACCEPTED', '1621784562');
insert into relationships (rel_id, first_user_id, second_user_id, status, since) values (12, 8, 3,  'ACCEPTED', '1632164475');
insert into relationships (rel_id, first_user_id, second_user_id, status, since) values (13, 3, 8,  'ACCEPTED', '1632164475');
insert into relationships (rel_id, first_user_id, second_user_id, status, since) values (14, 9, 2,  'ACCEPTED', '1627230430');
insert into relationships (rel_id, first_user_id, second_user_id, status, since) values (15, 2, 9,  'ACCEPTED', '1627230430');

--Chat
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (1, 2, 10, '1640021779', 'Morbi non quam nec dui luctus rutrum. Nulla tellus. In sagittis dui vel nisl.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (2, 2, 5, '1634016491', 'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (3, 2, 10, '1637604185', 'Aenean lectus. Pellentesque eget nunc.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (4, 2, 9, '1633304894', 'Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (5, 2, 5, '1626681933', 'Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (6, 2, 9, '1619854172', 'Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (7, 2, 9, '1634293846', 'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (8, 2, 10, '1628610352', 'Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (9, 2, 5, '1624932166', 'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (10, 2, 10, '1641486246', 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (11, 2, 9, '1623325289', 'Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (12, 2, 10, '1629319506', 'Curabitur gravida nisi at nibh.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (13, 2, 10, '1649736511', 'Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam. Nam tristique tortor eu pede.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (14, 3, 4, '1646195138', 'Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (15, 3, 4, '1620487696', 'Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (16, 3, 3, '1624373424', 'Phasellus in felis. Donec semper sapien a libero. Nam dui.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (17, 3, 4, '1644163884', 'Curabitur in libero ut massa volutpat convallis.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (18, 3, 4, '1637245401', 'Ut tellus. Nulla ut erat id mauris vulputate elementum. Nullam varius.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (19, 3, 4, '1626653112', 'Integer ac neque.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (20, 3, 3, '1646698115', 'Morbi porttitor lorem id ligula.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (21, 3, 3, '1623244270', 'Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (22, 3, 4, '1642997632', 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (23, 3, 4, '1647432929', 'Nullam porttitor lacus at turpis.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (24, 4, 2, '1619699728', 'Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo. Aliquam quis turpis eget elit sodales scelerisque.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (25, 4, 2, '1636505297', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (26, 4, 9, '1629027513', 'Morbi a ipsum. Integer a nibh.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (27, 4, 2, '1627475300', 'Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (28, 4, 3, '1649387093', 'Duis ac nibh.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (29, 4, 2, '1648051246', 'Aliquam erat volutpat.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (30, 4, 2, '1637929542', 'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (31, 4, 9, '1621427975', 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (32, 4, 2, '1646841294', 'Etiam pretium iaculis justo. In hac habitasse platea dictumst.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (33, 5, 3, '1636687134', 'Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (34, 5, 2, '1622123186', 'Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (35, 5, 2, '1625880119', 'Quisque id justo sit amet sapien dignissim vestibulum.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (36, 5, 3, '1619223425', 'Integer a nibh. In quis justo.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (37, 5, 2, '1625413492', 'In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (38, 5, 3, '1631167753', 'Integer a nibh.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (39, 5, 3, '1629200773', 'Proin at turpis a pede posuere nonummy. Integer non velit.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (40, 5, 2, '1620285203', 'Phasellus sit amet erat. Nulla tempus.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (41, 5, 3, '1620989393', 'Nulla tellus. In sagittis dui vel nisl.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (42, 6, 4, '1640720343', 'Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (43, 6, 3, '1632985332', 'Integer ac neque.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (44, 6, 3, '1637060889', 'Ut at dolor quis odio consequat varius. Integer ac leo.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (45, 6, 3, '1641189444', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (46, 6, 4, '1648777026', 'Integer a nibh.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (47, 6, 3, '1627158690', 'Suspendisse potenti.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (48, 6, 3, '1649956616', 'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (49, 6, 4, '1628684251', 'Praesent id massa id nisl venenatis lacinia.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (50, 6, 4, '1645793971', 'Donec ut dolor.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (51, 6, 3, '1645226284', 'In congue. Etiam justo.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (52, 6, 2, '1641401736', 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (53, 6, 2, '1641774031', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (54, 6, 3, '1637405002', 'Nunc rhoncus dui vel sem. Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (55, 6, 4, '1634917458', 'Nunc rhoncus dui vel sem. Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (56, 6, 2, '1649739060', 'Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (57, 6, 3, '1638072023', 'Pellentesque at nulla.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (58, 6, 2, '1619900347', 'Etiam pretium iaculis justo. In hac habitasse platea dictumst. Etiam faucibus cursus urna.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (59, 7, 9, '1646448283', 'Nullam molestie nibh in lectus.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (60, 7, 9, '1629044674', 'Aenean auctor gravida sem.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (61, 7, 4, '1623034783', 'Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi.');
insert into chat (ch_id, group_id, sender_id, time_sent, message) values (62, 7, 9, '1649967559', 'Etiam pretium iaculis justo. In hac habitasse platea dictumst. Etiam faucibus cursus urna.');

--Feedback
insert into feedback (fb_id, u_id, fb_message, read) values (1, 2, 'Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis.', 'NOT_SEEN');
insert into feedback (fb_id, u_id, fb_message, read) values (2, 9, 'Praesent lectus. Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio.', 'SEEN');
insert into feedback (fb_id, u_id, fb_message, read) values (3, 4, 'Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.', 'NOT_SEEN');
insert into feedback (fb_id, u_id, fb_message, read) values (4, 2, 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', 'NOT_SEEN');
insert into feedback (fb_id, u_id, fb_message, read) values (5, 10, 'Suspendisse potenti. Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum.', 'SEEN');
insert into feedback (fb_id, u_id, fb_message, read) values (6, 3, 'Aliquam non mauris. Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus.', 'NOT_SEEN');
insert into feedback (fb_id, u_id, fb_message, read) values (7, 5, 'Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus.', 'SEEN');
insert into feedback (fb_id, u_id, fb_message, read) values (8, 9, 'Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla.', 'NOT_SEEN');

--Reports
insert into reports (rep_id, reporter_id, violator_id, rep_message, status) values (1, 6, 3, 'Suspendisse potenti.', 'PENDING');
insert into reports (rep_id, reporter_id, violator_id, rep_message, status) values (2, 10, 2, 'Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', 'PENDING');
insert into reports (rep_id, reporter_id, violator_id, rep_message, status) values (3, 9, 3, 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem.', 'PENDING');

--Todos
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed, group_name) values (1, 2, 'morbi non', 'Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh.', '1642932539', '1649375492', true, 'hac');
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed, group_name) values (2, 4, 'vitae consectetuer eget rutrum at', 'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus. Phasellus in felis. Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis.', '1643189640', '1647315612', false, 'at dolor quis odio consequat');
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed, group_name) values (3, 7, 'justo morbi', 'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti. Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris. Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet.', '1643543580', '1647147103', true, 'in hac habitasse platea dictumst');
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed, group_name) values (4, 1, 'diam neque vestibulum eget', 'Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis.', '1642866527', '1648225360', false, 'turpis a pede');
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed, group_name) values (5, 3, 'nunc rhoncus', 'Proin at turpis a pede posuere nonummy. Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque. Duis bibendum.', '1641362884', '1646138318', false, 'eu mi nulla ac enim');
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed, group_name) values (6, 1, 'pellentesque volutpat dui maecenas tristique', 'Etiam justo. Etiam pretium iaculis justo. In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus. Nulla ut erat id mauris vulputate elementum. Nullam varius.', '1642841747', '1650463057', true, 'quam nec dui luctus');
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed, group_name) values (7, 3, 'convallis duis consequat dui nec', 'Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi. Nulla ac enim.', '1642969942', '1648517693', true, 'justo eu massa');
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed, group_name) values (8, 3, 'ut massa volutpat convallis morbi', 'Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis.', '1643790954', '1644964399', true, 'ac diam cras pellentesque');
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed, group_name) values (9, 7, 'praesent id', 'Maecenas pulvinar lobortis est. Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi.', '1642242271', '1649874173', false, 'sapien');
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed, group_name) values (10, 3, 'in eleifend', 'Etiam pretium iaculis justo. In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.', '1643162631', '1648516671', false, 'metus aenean fermentum donec ut');
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed, group_name) values (11, 5, 'nibh in quis', 'Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat.', '1644333635', '1648407020', false, 'nec');
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed, group_name) values (12, 2, 'amet cursus', 'Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat. In congue. Etiam justo. Etiam pretium iaculis justo. In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus. Nulla ut erat id mauris vulputate elementum.', '1642292300', '1645794260', false, 'interdum eu');
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed) values (13, 3, 'elementum nullam varius nulla facilisi', 'Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.', '1641003023', '1645285960', true);
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed) values (14, 5, 'vitae nisl aenean', 'Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero. Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh. In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam.', '1642114240', '1649572944', false);
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed) values (15, 1, 'imperdiet et commodo vulputate justo', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.', '1643652602', '1644735688', true);
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed) values (16, 3, 'rhoncus mauris enim leo', 'In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices.', '1642056836', '1649744735', true);
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed) values (17, 6, 'vel', 'Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.', '1643039478', '1645399518', true);
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed) values (18, 4, 'accumsan tortor', 'In congue. Etiam justo. Etiam pretium iaculis justo.', '1644245939', '1645542717', false);
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed) values (19, 3, 'platea dictumst', 'Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus. In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt.', '1643840622', '1647857402', false);
insert into todos (td_id, user_id, todo_name, todo_description, from_time, to_time, completed) values (20, 1, 'eget tempus vel pede', 'Aenean sit amet justo. Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo.', '1643287652', '1648286156', false);
