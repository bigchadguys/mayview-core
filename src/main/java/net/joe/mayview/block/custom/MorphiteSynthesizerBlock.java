package net.joe.mayview.block.custom;

import com.mojang.serialization.MapCodec;
import net.joe.mayview.block.entity.ModBlockEntities;
import net.joe.mayview.block.entity.custom.MorphiteSynthesizerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MorphiteSynthesizerBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final MapCodec<MorphiteSynthesizerBlock> CODEC = simpleCodec(MorphiteSynthesizerBlock::new);
    private static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);

    public MorphiteSynthesizerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(LIT, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, LIT);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new MorphiteSynthesizerBlockEntity(pos, state);
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, @NotNull Level level, @NotNull BlockPos pos, BlockState pNewState, boolean pIsMoving) {
        if (state.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof MorphiteSynthesizerBlockEntity morphiteSynthesizerBlockEntity) {
                morphiteSynthesizerBlockEntity.drops();
            }
        }

        super.onRemove(state, level, pos, pNewState, pIsMoving);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, Level level, @NotNull BlockPos pos,
                                                       @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof MorphiteSynthesizerBlockEntity morphiteSynthesizerBlockEntity) {
                player.openMenu(new SimpleMenuProvider(morphiteSynthesizerBlockEntity, Component.literal("Morphite Synthesizer")), pos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        if (level.isClientSide()) {
            return null;
        }

        return createTickerHelper(blockEntityType, ModBlockEntities.MORPHITE_SYNTHESIZER_BLOCK_ENTITY.get(),
                (level1, pos, state1, pBlockEntity) -> pBlockEntity.tick(level1, pos, state1));
    }

    @Override
    public void animateTick(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (!state.getValue(LIT)) {
            return;
        }

        double xPos = (double) pos.getX() + 0.5;
        double yPos = pos.getY();
        double zPos = (double) pos.getZ() + 0.5;
        if (random.nextDouble() < 0.15) {
            level.playLocalSound(xPos, yPos, zPos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0f, 1.0f, false);
        }

        Direction direction = state.getValue(FACING);
        Direction.Axis axis = direction.getAxis();

        double defaultOffset = random.nextDouble() * 0.6 - 0.3;
        double xOffsets = axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52 : defaultOffset;
        double yOffset = random.nextDouble() * 6.0 / 8.0;
        double zOffset = axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52 : defaultOffset;

        level.addParticle(ParticleTypes.SMOKE, xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);

        if (level.getBlockEntity(pos) instanceof MorphiteSynthesizerBlockEntity morphiteSynthesizerBlockEntity && !morphiteSynthesizerBlockEntity.itemHandler.getStackInSlot(0).isEmpty() && !morphiteSynthesizerBlockEntity.itemHandler.getStackInSlot(1).isEmpty() && !morphiteSynthesizerBlockEntity.itemHandler.getStackInSlot(2).isEmpty()) {
            level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, morphiteSynthesizerBlockEntity.itemHandler.getStackInSlot(2)),
                    xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);
        }
    }
}
