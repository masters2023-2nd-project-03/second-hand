import iconfiles from '../../assets/icons/index';

type IconName = keyof typeof iconfiles;

interface IconProps {
  name?: IconName;
  width?: string;
  height?: string;
  fill?: string;
}

const Icon: React.FC<IconProps> = ({
  name = 'arrowUp',
  width = '24',
  height = '24',
  fill = 'black',
}) => {
  const IconComponent = iconfiles[name];

  return (
    <div style={{ width: `${width}px`, height: `${height}px` }}>
      <IconComponent width={width} height={height} fill={fill} />
    </div>
  );
};

export default Icon;
