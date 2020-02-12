
DELETE FROM color;

INSERT INTO color(name, hex, rgb, custom) VALUES('white', '#ffffff', ARRAY [255, 255, 255], 'f'),
                                                ('black', '#000000', ARRAY[0, 0, 0], 'f'),
                                                ('red', '#ff0000', ARRAY[255, 0, 0], 'f'),
                                                ('green', '#008000', ARRAY[0, 128, 0], 'f'),
                                                ('blue', '#0000ff', ARRAY[0, 0, 255], 'f'),
                                                ('royalblue', '#4169e1', ARRAY[65, 105, 225], 'f'),
                                                ('limegreen', '#32cd32', ARRAY[50, 205, 50], 'f'),
                                                ('yellow', '#ffff00', ARRAY[255, 255, 0], 'f'),
                                                ('orange', '#ffa500', ARRAY[255, 165, 0], 'f'),
                                                ('salmon', '#fa8072', ARRAY[250, 128, 122], 'f'),
                                                ('hotpink', '#ff69b4', ARRAY[255, 105, 180], 'f'),
                                                ('ghostwhite', '#f8f8ff', ARRAY[248, 248, 255], 'f'),
                                                ('gray', '#808080', ARRAY[128, 128, 128], 'f'),
                                                ('academydarkgreen', '#047364', ARRAY[4, 115, 100], 't'),
                                                ('soveltored', '#e72533', ARRAY[231, 37, 51], 't');
