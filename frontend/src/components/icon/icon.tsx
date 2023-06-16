import iconfiles from '../../assets/icons/index';

interface IconProps {
  name?: keyof typeof iconfiles;
  width?: string;
  height?: string;
  fill?: string;
}

const Icon: React.FC<IconProps> = ({
  name = 'arrowUp',
  width = '16',
  height = '16',
  fill = 'var(--color-gray-1000)',
}) => {
  const IconComponent = iconfiles[name];

  return (
    <div style={{ width: `${width}px`, height: `${height}px` }}>
      {IconComponent && (
        <IconComponent width={width} height={height} fill={fill} />
      )}
    </div>
  );
};

export default Icon;
